package com.evamp.saanga.bankmanagement.requestresponse;

import jdk.jfr.Category;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponse {

    private String responseCode;
    private String responseBody;
    private long transactionAverage;

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

    public long getTransactionAverage() {
        return transactionAverage;
    }

    public void setTransactionAverage(long transactionAverage) {
        this.transactionAverage = transactionAverage;
    }
}
