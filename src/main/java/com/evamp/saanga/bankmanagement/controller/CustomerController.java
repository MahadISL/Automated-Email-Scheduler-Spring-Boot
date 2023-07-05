package com.evamp.saanga.bankmanagement.controller;

import com.evamp.saanga.bankmanagement.exception.CustomException;
import com.evamp.saanga.bankmanagement.model.Transaction;
import com.evamp.saanga.bankmanagement.model.User;
import com.evamp.saanga.bankmanagement.repository.TransactionRepo;
import com.evamp.saanga.bankmanagement.repository.UserRepo;
import com.evamp.saanga.bankmanagement.requestresponse.*;
import com.evamp.saanga.bankmanagement.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("bank")
public class CustomerController {

    static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    SigninResponse signinResponse;

    @Autowired
    Transaction transaction;

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    SendMoneyResponse sendMoneyResponse;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AccountNoResponse accountNoResponse;

    @Autowired
    CheckBalanceResponse checkBalanceResponse;

    @Autowired
    User user;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/accountno")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AccountNoResponse> fetchAccountNo(@RequestBody AccountNoRequest cnic){

        log.info("INSIDE FETCH ACCOUNT NO CONTROLLER");

        log.info("FINDING SAVED USER...");
        User savedUser = userRepo.findByCnic(cnic.getCnic()).orElseThrow(
                () -> new CustomException("NO CUSTOMER PRESENT WITH CNIC = " + cnic.getCnic())
        );


        accountNoResponse.setAccountNo(savedUser.getaccountNo());
        accountNoResponse.setResponseCode(String.valueOf(HttpStatus.OK));
        accountNoResponse.setResponseBody("ACCOUNT NUMBER SUCCESSFULLY FETCHED!");
        log.info("SET RESPONSE OBJECT VALUES USING SAVED  OBJECT");

        return new ResponseEntity<>(accountNoResponse, HttpStatus.OK);
    }

    @GetMapping("/checkbalance")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<CheckBalanceResponse> checkBalance(@RequestBody CheckBalanceRequest request){

        log.info("INSIDE CHECK BALANCE CONTROLLER ");

        log.info("FINDING SAVED USER...");
        Integer accountNo = request.getAccountNo();
        User savedUser = userRepo.findByAccountNo(accountNo);
//                .orElseThrow(
//                () -> new CustomException("NO CUSTOMER PRESENT WITH ACCOUNT NO = " + request.getAccountNo())
//        );

        checkBalanceResponse.setBalance(savedUser.getBalance());
        checkBalanceResponse.setResponseCode(String.valueOf(HttpStatus.OK));
        checkBalanceResponse.setResponseBody("ACCOUNT BALANCE SUCCESSFULLY FETCHED!");
        log.info("SET RESPONSE OBJECT VALUES USING SAVED OBJECT");

        return new ResponseEntity<>(checkBalanceResponse, HttpStatus.OK);

    }

    @PostMapping("/sendmoney")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<SendMoneyResponse> sendMoney(@RequestBody SendMoneyRequest request){
        User user = userRepo.findByAccountNo(request.getSenderAccountNo());
        System.out.println(user);
        transaction.setAmount(request.getAmount());
        transaction.setDateAndTime(LocalDateTime.now());
        transaction.setTransactionReferenceNumber(123);
        transaction.setReceiverAccountNo(request.getReceiverAccountNo());
        transaction.setObj(user);

        transactionRepo.save(transaction);

        sendMoneyResponse.setAmount(request.getAmount());
        sendMoneyResponse.setResponseCode(String.valueOf(HttpStatus.OK));
        sendMoneyResponse.setResponseBody("MONEY TRANSFERED SUCCESSFULLY!");
        return new ResponseEntity<SendMoneyResponse>(sendMoneyResponse, HttpStatus.OK);

    }


    @PostMapping("/signin")
    ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
        System.out.println("Request landed in signin controller");
        JwtAuthenticationResponse jwtObject = authenticationService.signin(request);
        System.out.println(jwtObject);
        signinResponse.setToken(jwtObject.getToken());
        signinResponse.setResponseCode(String.valueOf(HttpStatus.OK));
        signinResponse.setResponseBody("USER SIGNED IN");
        return new ResponseEntity<>(signinResponse, HttpStatus.OK);
    }
}
