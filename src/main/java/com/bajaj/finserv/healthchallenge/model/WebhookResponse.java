package com.bajaj.finserv.healthchallenge.model;

public class WebhookResponse {
    private String webhook;
    private String accessToken;

    public WebhookResponse() {
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}