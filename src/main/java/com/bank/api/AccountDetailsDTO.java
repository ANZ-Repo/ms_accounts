package com.bank.api;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDTO {
    private Long accountId;
    private String accountName;
    private Long userId;
    private String accountType;
    private Date balanceDate;
    private String currency;
    private String balance;

}
