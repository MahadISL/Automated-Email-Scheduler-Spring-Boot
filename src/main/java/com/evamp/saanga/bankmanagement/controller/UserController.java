package com.evamp.saanga.bankmanagement.controller;

import com.evamp.saanga.bankmanagement.model.Transaction;
import com.evamp.saanga.bankmanagement.model.User;
import com.evamp.saanga.bankmanagement.repository.TransactionRepo;
import com.evamp.saanga.bankmanagement.repository.UserRepo;
import com.evamp.saanga.bankmanagement.requestresponse.TransactionResponse;
import com.evamp.saanga.bankmanagement.requestresponse.UserRequest;
import com.evamp.saanga.bankmanagement.service.EmailService;
import com.evamp.saanga.bankmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    TransactionResponse transactionResponse;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/sendemail")
    ResponseEntity<TransactionResponse> sendTransactionEmail(@RequestBody UserRequest request) throws IOException, MessagingException {
        Iterable<Integer> idList = Collections.singleton(request.getAccountNo());
//        Integer trnN = request.getTrn();
        List<Transaction> transactionList = transactionRepo.findAllByObjAccountNoIn(idList);
        System.out.println(transactionList);
        userService.automatedEmailSchedule();
//        List<Transaction> transactionList = transactionRepo.findAllByTransactionReferenceNumber(trnN);
        long transactionAverage = userService.calculateTransactionAverage(transactionList);
        User obj = userRepo.findEmailByAccountNo(request.getAccountNo());
        String email = obj.getEmail();;
        emailService.sendEmail(email,"Automated Email to User", String.valueOf(transactionAverage));
        userService.calculateTransactionAverage(transactionList);
        transactionResponse.setResponseCode(String.valueOf(HttpStatus.OK));
        transactionResponse.setResponseBody("EMAIL SENT SUCCESSFULLY");
        transactionResponse.setTransactionAverage(transactionAverage);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

}
