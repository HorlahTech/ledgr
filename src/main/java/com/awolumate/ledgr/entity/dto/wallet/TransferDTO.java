package com.awolumate.ledgr.entity.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class TransferDTO {

String toAccountNumber;
String myAccountNumber;
int amount;

}
