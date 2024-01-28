package com.example.xyzhotelddd.http.dto;

import java.math.BigDecimal;

public class DepositDTO {
    private Long idClient;
    private BigDecimal amount;
    private String currency;


    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
