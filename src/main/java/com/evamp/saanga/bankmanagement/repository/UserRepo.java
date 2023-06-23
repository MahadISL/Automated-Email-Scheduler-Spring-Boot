package com.evamp.saanga.bankmanagement.repository;

import com.evamp.saanga.bankmanagement.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {

    User findEmailByAccountNo(Integer accountNo);
    List<User> findAll();
}
