package com.bank.controller;

import com.bank.api.AccountDetailsDTO;
import com.bank.application.AccountService;
import com.bank.api.TransactionDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountDetailsController {

    @Autowired
    private AccountService accountService;

    @GetMapping("user/{user_id}/accounts")
    public ResponseEntity<List<AccountDetailsDTO>> getAccountDetails(
            @PathVariable("user_id") Long userId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return new ResponseEntity<>(accountService.getAccountDetailsList(userId, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("accounts/{account_id}/transactions")
    public ResponseEntity<List<TransactionDetailsDTO>> getTransactionDetails(
            @PathVariable("account_id") Long accountId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "transDate") String sortBy) {
        return new ResponseEntity<>(accountService.getTransactionDetailsList(pageNo, pageSize, sortBy, accountId), HttpStatus.OK);
    }
}
