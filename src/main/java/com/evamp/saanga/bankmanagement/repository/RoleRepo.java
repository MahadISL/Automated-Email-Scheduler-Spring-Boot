package com.evamp.saanga.bankmanagement.repository;

import com.evamp.saanga.bankmanagement.model.ERole;
import com.evamp.saanga.bankmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
