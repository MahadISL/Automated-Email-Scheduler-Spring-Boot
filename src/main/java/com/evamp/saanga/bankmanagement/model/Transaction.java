package com.evamp.saanga.bankmanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "transactionReferenceNumber")
    private Integer transactionReferenceNumber;

    @Column(name = "receiver_account_no#")
    private Integer receiverAccountNo;

    public User getObj() {
        return obj;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_account_no#")
    User obj;



    public Transaction() {
    }

    public Transaction(int id, LocalDateTime dateAndTime, Integer amount, Integer transactionReferenceNumber, Integer receiverAccountNo, User obj) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.amount = amount;
        this.transactionReferenceNumber = transactionReferenceNumber;
        this.receiverAccountNo = receiverAccountNo;
        this.obj = obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTransactionReferenceNumber() {
        return transactionReferenceNumber;
    }

    public void setTransactionReferenceNumber(Integer transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }


    public Integer getReceiverAccountNo() {
        return receiverAccountNo;
    }

    public void setReceiverAccountNo(Integer receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }
}
