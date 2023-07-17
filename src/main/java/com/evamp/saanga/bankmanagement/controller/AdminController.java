package com.evamp.saanga.bankmanagement.controller;

import com.evamp.saanga.bankmanagement.exception.CustomException;
import com.evamp.saanga.bankmanagement.model.ERole;
import com.evamp.saanga.bankmanagement.model.Role;
import com.evamp.saanga.bankmanagement.model.User;
import com.evamp.saanga.bankmanagement.repository.RoleRepo;
import com.evamp.saanga.bankmanagement.repository.UserRepo;
import com.evamp.saanga.bankmanagement.requestresponse.AccountNoRequest;
import com.evamp.saanga.bankmanagement.requestresponse.AccountNoResponse;
import com.evamp.saanga.bankmanagement.requestresponse.CreateAccountRequest;
import com.evamp.saanga.bankmanagement.requestresponse.CreateAccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("bank")
public class AdminController {

    static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    CreateAccountResponse createAccountResponse;

    @Autowired
    User user;

    @Autowired
    AccountNoResponse accountNoResponse;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @PostMapping("/createaccount")
//    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest accountInfo){

        log.info("INSIDE CREATE ACCOUNT CONTROLLER");

        Set<String> strRoles = accountInfo.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach( role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(()-> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "user":
                    Role userRole1 = roleRepo.findByName(ERole.ROLE_USER)
                            .orElseThrow(()-> new RuntimeException("Error: Role is not found"));
                    roles.add(userRole1);

                    break;
                default:
                    Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });

        user.setId(accountInfo.getId());
        user.setFirstName(accountInfo.getFirstName());
        user.setLastName(accountInfo.getLastName());
        user.setEmail(accountInfo.getEmail());
        user.setCnic(accountInfo.getCnic());
        user.setBalance(accountInfo.getBalance());
        user.setRoles(roles);
        log.info("SET USER OBJECT VALUE S USING REQUEST OBJECT");

        log.info("Saving User..");
        User user2 = userRepo.save(user);

        log.info("FINDING SAVED USER...");
        User userSavedInDb = userRepo.findByCnic(accountInfo.getCnic()).orElseThrow(
                () -> new CustomException("NO CUSTOMER PRESENT WITH CNIC = " + accountInfo.getCnic())
        );

        User userRoleSavedInDB = userRepo.findByCnic(accountInfo.getCnic()).orElseThrow(
                () -> new CustomException("NO CUSTOMER PRESENT WITH CNIC = " + accountInfo.getCnic())
        );

        createAccountResponse.setAccountNo(userSavedInDb.getaccountNo());
        createAccountResponse.setId(userSavedInDb.getId());
        createAccountResponse.setFirstName(userSavedInDb.getFirstName());
        createAccountResponse.setLastName(userSavedInDb.getLastName());
        createAccountResponse.setEmail(userSavedInDb.getEmail());
        createAccountResponse.setCnic(userSavedInDb.getCnic());
        createAccountResponse.setBalance(userSavedInDb.getBalance());
        createAccountResponse.setResponseBody("ACCOUNT CREATED SUCCESSFULLY!");
        createAccountResponse.setResponseCode(String.valueOf(HttpStatus.OK));
        log.info("SET RESPONSE OBJECT VALUES USING SAVED  OBJECT");

        return new ResponseEntity<>(createAccountResponse, HttpStatus.OK);
    }

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
}
