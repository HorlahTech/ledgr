package com.awolumate.ledgr;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;

import com.awolumate.ledgr.controllers.WalletController;
import com.awolumate.ledgr.entity.Transfer;
import com.awolumate.ledgr.entity.Wallet;
import com.awolumate.ledgr.entity.dto.wallet.TransferDTO;
import com.awolumate.ledgr.repositories.TransferRepository;
import com.awolumate.ledgr.repositories.WalletRepository;
import com.awolumate.ledgr.services.WalletService;

@SpringBootTest
@Import(TestcontainersConfiguration.class) 
class LedgrApplicationTests {
	@Autowired 
WalletRepository walletRepo;
@Autowired 
TransferRepository transferRepo;
@Autowired 
WalletService walletService;

	@BeforeEach 
void crearData(){
	walletRepo.deleteAll();
	transferRepo.deleteAll();
}

	@Test
	void testTransfer() {
		Wallet sender = new Wallet( "kemi", "12345", 2000);
Wallet receiver = new Wallet( "idowu", "54321", 2000);
		TransferDTO  tf = new TransferDTO(receiver.getAccountNumber(),sender.getAccountNumber(), 100 );
		walletService.transer(tf, "rewgrerttr");
    assertThat(balanceOf("11111")).isEqualTo(700);
	}
   private int balanceOf(String accountNumber) {
        return walletRepo.findByAccountNumber(accountNumber).orElseThrow().getBalance();
    }
}
