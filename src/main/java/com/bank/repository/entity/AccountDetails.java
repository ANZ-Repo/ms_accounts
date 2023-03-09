package com.bank.repository.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {

    @Id
    private Long accountId;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "balance_date")
    private Date balanceDate;
    @Column(name = "currency")
    private String currency;
    @Column(name = "balance")
    private String balance;

}
