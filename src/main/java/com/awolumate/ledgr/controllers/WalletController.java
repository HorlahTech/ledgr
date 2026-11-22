package com.awolumate.ledgr.controllers;

import com.awolumate.ledgr.repositories.TransferRepository;
import java.nio.file.WatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.awolumate.ledgr.entity.Wallet;
import com.awolumate.ledgr.entity.dto.wallet.DepositWallet;
import com.awolumate.ledgr.entity.dto.wallet.TransferDTO;
import com.awolumate.ledgr.entity.dto.wallet.WalletRequest;
import com.awolumate.ledgr.services.WalletService;


@Controller 
public class WalletController {
private final TransferRepository transferRepository;
@Autowired 
    WalletService walletService;
WalletController(TransferRepository transferRepository) {
    this.transferRepository = transferRepository;
}
    @PostMapping("/wallet")
    public ResponseEntity<Wallet> createWallet(@RequestBody WalletRequest walletRequest) throws Exception {

Wallet wallet = walletService.createWallet(walletRequest);
        return ResponseEntity.status(201).body(wallet);
    }
    
@PostMapping("/deposit")
public ResponseEntity<Wallet> deposit(@RequestBody DepositWallet deposit) {
return ResponseEntity.ok(walletService.depositToWallet(deposit));
}

@PostMapping("/transfer")
public ResponseEntity<Wallet> transfer(@RequestBody TransferDTO transferDTO, @RequestHeader("X-Idempotency-Key") String idempotencyKey ) {


    return  ResponseEntity.ok(walletService.transer(transferDTO, idempotencyKey));
    

}


}
