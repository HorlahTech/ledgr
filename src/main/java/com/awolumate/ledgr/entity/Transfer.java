package com.awolumate.ledgr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor 
@AllArgsConstructor 
@Data 
@Table 
@Entity 
public class Transfer {
@Id 
    @GeneratedValue(strategy =  GenerationType.AUTO) 
 private   Long id;
String toAccountNumber;
String myAccountNumber;
int amount;
String idempotencyKey;
String status;

public Transfer(String toAccountNumber,String myAccountNumber, int amount,String idempotencyKey){
    this.toAccountNumber = toAccountNumber;
    this.myAccountNumber = myAccountNumber;
    this.amount = amount;
    this.idempotencyKey = idempotencyKey;
}

}
