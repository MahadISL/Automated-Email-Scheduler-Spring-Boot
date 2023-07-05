package com.evamp.saanga.bankmanagement.service;

import com.evamp.saanga.bankmanagement.requestresponse.JwtAuthenticationResponse;
import com.evamp.saanga.bankmanagement.requestresponse.SigninRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    private JwtUtils jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse signin(SigninRequest request) {
        System.out.println("Request recived in signin service");

        System.out.println("Generating JWT token");
        var jwt = jwtService.generateJwtToken(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())));
        System.out.println(jwt);
        return new JwtAuthenticationResponse(jwt);


    }
}
