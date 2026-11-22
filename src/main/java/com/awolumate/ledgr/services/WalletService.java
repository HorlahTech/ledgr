package com.awolumate.ledgr.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awolumate.ledgr.entity.Transfer;
import com.awolumate.ledgr.entity.Wallet;
import com.awolumate.ledgr.entity.dto.wallet.DepositWallet;
import com.awolumate.ledgr.entity.dto.wallet.TransferDTO;
import com.awolumate.ledgr.entity.dto.wallet.WalletRequest;
import com.awolumate.ledgr.exception.NotFoundException;
import com.awolumate.ledgr.exception.ViolatedException;
import com.awolumate.ledgr.repositories.TransferRepository;
import com.awolumate.ledgr.repositories.WalletRepository;

import jakarta.transaction.Transactional;

@Service 
public class WalletService {
    @Autowired 
WalletRepository walletRepository;
 @Autowired 
TransferRepository transferRepository;


public Wallet  createWallet(WalletRequest requestWallet) throws Exception{
final String owner = requestWallet.getOwner();
if (owner.isBlank() || owner.isEmpty()) {
    throw new Exception("Wallet must have a onwer Information");
}

final  Random rand = new Random();
  String accountNumber;
 do {
    int accNumber = 10_000 + rand.nextInt(90_000);
accountNumber = String.valueOf(accNumber);

 } while (walletRepository.existsByAccountNumber(accountNumber));
if (!accountNumber.isBlank() && !accountNumber.isEmpty()) {
     Wallet wallet = new Wallet( requestWallet.getOwner(), accountNumber, 0);
    return  walletRepository.save(wallet);
}
return null;


}



public Wallet depositToWallet( DepositWallet deposit){

String accountNumber  = deposit.getAccountNumber();
if (accountNumber.isBlank() ||  accountNumber.isEmpty()  ) {
    throw new NotFoundException("Account Number Cannot Be empty");
}
if (deposit.getAmount() <= 0) {
    throw new ViolatedException("Deposit a valid Amount");
}
Wallet wallet =  walletRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new NotFoundException("Account Not Found"));
int currentBalance = wallet.getBalance();
wallet.setBalance(currentBalance + deposit.getAmount());
return walletRepository.save(wallet);

}
@Transactional 
public  synchronized Wallet transer(TransferDTO tansferWallet, String idempotencyKey){
String myAccountNumber  = tansferWallet.getMyAccountNumber();
String toAccountNumber  = tansferWallet.getMyAccountNumber();
    if (myAccountNumber.isBlank() ||  myAccountNumber.isEmpty() ||toAccountNumber.isBlank() ||  toAccountNumber.isEmpty()  ) {
    throw new NotFoundException("Account Number Cannot Be empty");
}
Wallet myAccount = walletRepository.findByAccountNumber(myAccountNumber).orElseThrow(()-> new NotFoundException("Cannot find Sender Account"));
Wallet toAccount = walletRepository.findByAccountNumber(toAccountNumber).orElseThrow(()-> new NotFoundException("Cannot find Recipieent Account"));
if (tansferWallet.getAmount() <= 0) {
    throw new ViolatedException("Transfer a valid Amount");
}
if (myAccount.getBalance() <= 0) {
    throw new ViolatedException("Sender accound does not have  a Sufficient Balance");
}
Transfer newTransfer = new Transfer( myAccountNumber, toAccountNumber, tansferWallet.getAmount(), idempotencyKey );
if (transferRepository.existsByIdempotencyKey(idempotencyKey)) {
    newTransfer.setStatus("FAILED");
    transferRepository.save(newTransfer);
    throw new ViolatedException("Looks like a duplicate Transaction");
    
}
int myBalance = myAccount.getBalance() - tansferWallet.getAmount();
myAccount.setBalance(myBalance);
int toBalance = toAccount.getBalance() + tansferWallet.getAmount();
toAccount.setBalance(toBalance);
walletRepository.save(toAccount);
walletRepository.save(myAccount);
newTransfer.setStatus("SUCCESSFUL");
transferRepository.save(newTransfer);
return myAccount;
}
}