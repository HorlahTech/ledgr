package com.awolumate.ledgr;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;


import com.awolumate.ledgr.entity.Wallet;
import com.awolumate.ledgr.entity.dto.wallet.TransferDTO;
import com.awolumate.ledgr.exception.NotFoundException;
import com.awolumate.ledgr.exception.ViolatedException;
import com.awolumate.ledgr.repositories.TransferRepository;
import com.awolumate.ledgr.repositories.WalletRepository;
import com.awolumate.ledgr.services.WalletService;

@SpringBootTest
@Import(PostgressContainer.class) 
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
	walletRepo.save(sender);
walletRepo.save(receiver);
}

	Wallet sender = new Wallet( "kemi", "12345", 2000);
Wallet receiver = new Wallet( "idowu", "54321", 2000);
	@Test
	void testTransfer() {
	

		TransferDTO  tf = new TransferDTO(receiver.getAccountNumber(),sender.getAccountNumber(), 100 );
		walletService.transer(tf, "rewgrerttr");
    assertThat(balanceOf(sender.getAccountNumber())).isEqualTo(1900);
	
assertThat(balanceOf(receiver.getAccountNumber())).isEqualTo(2100);}
   private int balanceOf(String accountNumber) {
        return walletRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new NotFoundException("Can't find this wallet")).getBalance();
    }

	@Test
	void overDraftTest(){
	TransferDTO  tf = new TransferDTO(receiver.getAccountNumber(),sender.getAccountNumber(), 2000 );
	

		 assertThatThrownBy(()->walletService.transer(tf, "wegedsdgdf")).isInstanceOf(ViolatedException.class);
	


	}
}
