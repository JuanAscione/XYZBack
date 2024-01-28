package com.example.xyzhotelddd.http.dto;

public class DepositResponse {
    private String message;

    public DepositResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
