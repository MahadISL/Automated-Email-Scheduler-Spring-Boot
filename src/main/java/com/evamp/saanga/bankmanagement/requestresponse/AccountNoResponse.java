package com.evamp.saanga.bankmanagement.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class AccountNoResponse {

    private Integer accountNo;
    private String responseCode;
    private String responseBody;

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
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
