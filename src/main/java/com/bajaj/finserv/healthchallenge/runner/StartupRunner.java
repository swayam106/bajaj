package com.bajaj.finserv.healthchallenge.runner;

import com.bajaj.finserv.healthchallenge.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    private final WebhookService webhookService;

    public StartupRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Application started, executing Bajaj Finserv Health Challenge workflow...");

        try {
            // Execute the complete workflow on startup
            String result = webhookService.executeCompleteWorkflow().block();
            logger.info("Workflow completed successfully. Result: {}", result);
        } catch (Exception e) {
            logger.error("Error executing workflow on startup: {}", e.getMessage(), e);
            throw e;
        }

        logger.info("Bajaj Finserv Health Challenge workflow execution completed");
    }
}