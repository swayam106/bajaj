package com.bajaj.finserv.healthchallenge.service;

import com.bajaj.finserv.healthchallenge.model.SqlSubmissionRequest;
import com.bajaj.finserv.healthchallenge.model.WebhookGenerateRequest;
import com.bajaj.finserv.healthchallenge.model.WebhookResponse;
import com.bajaj.finserv.healthchallenge.util.SqlSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);

    private final WebClient webClient;
    private final SqlSolver sqlSolver;

    @Value("${bajaj.webhook.base-url}")
    private String baseUrl;

    @Value("${bajaj.webhook.generate-path}")
    private String generatePath;

    @Value("${bajaj.webhook.test-path}")
    private String testPath;

    @Value("${bajaj.user.name}")
    private String userName;

    @Value("${bajaj.user.reg-no}")
    private String regNo;

    @Value("${bajaj.user.email}")
    private String email;

    public WebhookService(SqlSolver sqlSolver) {
        this.webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .build();
        this.sqlSolver = sqlSolver;
    }

    /**
     * Step 1: Generate webhook by sending POST request with user details
     */
    public Mono<WebhookResponse> generateWebhook() {
        logger.info("Generating webhook for user: {} with regNo: {}", userName, regNo);

        WebhookGenerateRequest request = new WebhookGenerateRequest(userName, regNo, email);

        return webClient.post()
                .uri(baseUrl + generatePath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WebhookResponse.class)
                .doOnSuccess(response -> logger.info("Webhook generated successfully: {}", response.getWebhook()))
                .doOnError(error -> logger.error("Error generating webhook: {}", error.getMessage()));
    }

    /**
     * Step 2: Submit SQL query to the webhook
     */
    public Mono<String> submitSqlQuery(String accessToken) {
        logger.info("Submitting SQL query using access token");

        String sqlQuery = sqlSolver.getSqlQuery(regNo);
        logger.info("Generated SQL query: {}", sqlQuery);

        SqlSubmissionRequest request = new SqlSubmissionRequest(sqlQuery);

        return webClient.post()
                .uri(baseUrl + testPath)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> logger.info("SQL query submitted successfully: {}", response))
                .doOnError(error -> logger.error("Error submitting SQL query: {}", error.getMessage()));
    }

    /**
     * Complete workflow: Generate webhook and submit SQL query
     */
    public Mono<String> executeCompleteWorkflow() {
        logger.info("Starting complete workflow execution");

        return generateWebhook()
                .flatMap(webhookResponse -> {
                    logger.info("Webhook response received, proceeding to submit SQL query");
                    return submitSqlQuery(webhookResponse.getAccessToken());
                })
                .doOnSuccess(result -> logger.info("Complete workflow executed successfully"))
                .doOnError(error -> logger.error("Error in complete workflow: {}", error.getMessage()));
    }
}