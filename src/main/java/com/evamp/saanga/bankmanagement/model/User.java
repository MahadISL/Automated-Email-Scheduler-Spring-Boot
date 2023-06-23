package com.evamp.saanga.bankmanagement.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "account_no#")
    private Integer accountNo;

    @Column(name = "user_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactionObjList;

    public User(int id, String firstName, String lastName, String email, Integer accountNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountNo = accountNo;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getaccountNo() {
        return accountNo;
    }

    public void setaccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

}
