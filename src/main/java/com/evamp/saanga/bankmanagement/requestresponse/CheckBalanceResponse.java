package com.evamp.saanga.bankmanagement.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class CheckBalanceResponse {

    private String responseCode;
    private String responseBody;
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
