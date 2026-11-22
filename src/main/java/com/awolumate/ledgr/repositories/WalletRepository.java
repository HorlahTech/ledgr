package com.awolumate.ledgr.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.awolumate.ledgr.entity.Wallet;
@Repository 
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);

}
