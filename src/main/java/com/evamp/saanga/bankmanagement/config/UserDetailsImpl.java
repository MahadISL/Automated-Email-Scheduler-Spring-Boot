package com.evamp.saanga.bankmanagement.config;

import com.evamp.saanga.bankmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl {

//    @Autowired
//    User user;
//
//    private int id;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String cnic;
//    private Double balance;
//    private Collection<? extends GrantedAuthority> authorities;
//
//
//
//    public UserDetailsImpl(int id, String firstName, String lastName, String email,
//                           String cnic, Double balance, Collection<? extends GrantedAuthority> authorities ){
//            this.id = id;
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.email = email;
//            this.cnic = cnic;
//            this.balance = balance;
//            this.authorities = authorities;
//    }
//
//    public static UserDetailsImpl build(User user, authorities1) {
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//                .collect(Collectors.toList());
//
//        return new UserDetailsImpl(
//                user.getId(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getEmail(),
//                user.getCnic(),
//                user.getBalance()),
//                authorities);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return String.valueOf(user.getaccountNo());
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
