package com.awolumate.ledgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awolumate.ledgr.entity.Transfer;

import java.util.Optional;


public interface TransferRepository extends JpaRepository<Transfer, Long> {
   Optional<Transfer>  findByIdempotencyKey(String idempotencyKey);
boolean existsByIdempotencyKey(String idempotencyKey);
}
