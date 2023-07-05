package com.evamp.saanga.bankmanagement.repository;

import com.evamp.saanga.bankmanagement.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer> {

    User findEmailByAccountNo(Integer accountNo);

    User findByEmail(String email);
    User findByFirstName(String firstName);
    Optional<User> findByCnic(String cnic);
    List<User> findAll();

    Integer findAccountNoByEmail(String email);
    Integer findAccountNoByCnic(String cnic);
    Boolean findById(int id);

    User findByAccountNo(Integer accountNo);


}
