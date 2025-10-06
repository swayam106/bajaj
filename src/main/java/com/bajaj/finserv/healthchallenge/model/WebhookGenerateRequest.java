package com.bajaj.finserv.healthchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookGenerateRequest {
    private String name;

    @JsonProperty("regNo")
    private String regNo;

    private String email;

    public WebhookGenerateRequest() {
    }

    public WebhookGenerateRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}