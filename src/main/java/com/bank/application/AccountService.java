package com.bank.application;

import com.bank.api.AccountDetailsDTO;
import com.bank.repository.entity.TransactionDetails;
import com.bank.api.TransactionDetailsDTO;
import com.bank.repository.entity.AccountDetails;
import com.bank.repository.AccountDetailsRepository;
import com.bank.repository.TransactionDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    public List<AccountDetailsDTO> getAccountDetailsList(Long userId, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findByUserId(userId, paging);
        if (accountDetailsList.isEmpty()) {
            log.info("message=\"No Accounts found for User Id " + userId);
            throw new NoSuchElementException("Customer Id Not Found");
        }
        return accountDetailsList.stream().map(accountDetails -> AccountDetailsDTO.builder()
                .userId(accountDetails.getUserId())
                .accountId(accountDetails.getAccountId())
                .accountType(accountDetails.getAccountType())
                .accountName(accountDetails.getAccountName())
                .balanceDate(accountDetails.getBalanceDate())
                .balance(accountDetails.getBalance())
                .currency(accountDetails.getCurrency())
                .build()).collect(Collectors.toList());
    }

    public List<TransactionDetailsDTO> getTransactionDetailsList(Integer pageNo, Integer pageSize, String sortBy, Long accountId) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findByAccountAccountId(accountId, paging);
        if (transactionDetailsList.isEmpty()) {
            log.info("message=\"No Transaction History for Account Id " + accountId);
            throw new NoSuchElementException("Transactions Not Found");
        }

        return transactionDetailsList.stream().map(transactionDetails -> TransactionDetailsDTO.builder()
                .accountId(transactionDetails.getAccount().getAccountId())
                .accountName(transactionDetails.getAccount().getAccountName())
                .creditAmount(transactionDetails.getCreditAmount())
                .debitAmount(transactionDetails.getDebitAmount())
                .transType(transactionDetails.getTransType())
                .transDate(transactionDetails.getTransDate())
                .currency(transactionDetails.getCurrency())
                .build()).collect(Collectors.toList());
    }

}
