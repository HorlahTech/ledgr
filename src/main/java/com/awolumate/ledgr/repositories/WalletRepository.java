package com.awolumate.ledgr.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.awolumate.ledgr.entity.Wallet;

import jakarta.persistence.LockModeType;
@Repository 
public interface WalletRepository extends JpaRepository<Wallet, Long> {
       @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);



}
