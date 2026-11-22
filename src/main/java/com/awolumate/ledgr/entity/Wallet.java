package com.awolumate.ledgr.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Wallet
 */
@AllArgsConstructor 
@NoArgsConstructor 
@Data 
@Entity 
@Table

public class Wallet {
    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY) 
  private  Long id;


    String owner;

      @Column(name = "account_number", unique = true, nullable = false, length = 5)
    String accountNumber;
    
    Integer balance;

public Wallet(String owner,   String accountNumber, Integer balance){
this.owner = owner;
this.accountNumber = accountNumber;
this.balance = balance;
}
    
}