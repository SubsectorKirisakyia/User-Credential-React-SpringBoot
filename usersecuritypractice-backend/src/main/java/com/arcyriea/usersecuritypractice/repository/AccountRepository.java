package com.arcyriea.usersecuritypractice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arcyriea.usersecuritypractice.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("SELECT a FROM Account a WHERE a.email = :email")
    Optional<Account> findByEmail(@Param("email") String email);
    
}
