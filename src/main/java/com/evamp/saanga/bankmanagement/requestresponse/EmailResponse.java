package com.evamp.saanga.bankmanagement.requestresponse;

import org.springframework.stereotype.Component;

@Component
public class EmailResponse {

    private String message;
    private String transactionAverage;
    private String firstName;
    private String lastName;

    public EmailResponse(String message, String transactionAverage, String firstName, String lastName) {
        this.message = message;
        this.transactionAverage = transactionAverage;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EmailResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionAverage() {
        return transactionAverage;
    }

    public void setTransactionAverage(String transactionAverage) {
        this.transactionAverage = transactionAverage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return  message + " " + firstName + " " + lastName + "\n" +
                "Your transaction average for the day: " + transactionAverage + "\n";
    }
}
