package com.evamp.saanga.bankmanagement.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class SendMoneyResponse {

    private Integer amount;
    private String responseCode;
    private String responseBody;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
