package com.bank.api;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsDTO {
    private Long accountId;
    private String accountName;
    private Date transDate;
    private String currency;
    private String creditAmount;
    private String debitAmount;
    private String transType;
}
