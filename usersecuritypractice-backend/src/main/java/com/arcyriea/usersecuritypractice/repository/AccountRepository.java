package com.arcyriea.usersecuritypractice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arcyriea.usersecuritypractice.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("SELECT u FROM Account u WHERE u.email = :email")
    Optional<Account> findByEmail(@Param("email") String email);
    
}
