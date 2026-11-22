package com.awolumate.ledgr.entity.dto.wallet;

import lombok.Data;

@Data 
public class TransferDTO {
String toAccountNumber;
String myAccountNumber;
int amount;

}
