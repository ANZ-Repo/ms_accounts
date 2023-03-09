package com.bank;

import com.bank.api.AccountDetailsDTO;
import com.bank.application.AccountService;
import com.bank.repository.entity.AccountDetails;
import com.bank.repository.entity.TransactionDetails;
import com.bank.api.TransactionDetailsDTO;
import com.bank.repository.AccountDetailsRepository;
import com.bank.repository.TransactionDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountDetailsServiceTest {
    @Mock
    private AccountDetailsRepository accountDetailsRepository;

    @Mock
    private TransactionDetailsRepository transactionDetailsRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testAccountDetails() {
        Pageable paging = PageRequest.of(0, 10);
        when(accountDetailsRepository.findByUserId(eq(2L),eq(paging))).thenReturn(
           List.of(AccountDetails.builder()
                   .accountType("savings")
                   .accountId(Long.valueOf("12345"))
                   .accountName("accname")
                   .currency("AUD")
                   .balance(String.valueOf(100))
                   .build()));
    List<AccountDetailsDTO> accountDetailsList = accountService.getAccountDetailsList(2L,0,10);
    assertEquals(accountDetailsList.get(0).getAccountId(), 12345 );
    assertEquals(accountDetailsList.get(0).getAccountType(), "savings" );
    assertEquals(accountDetailsList.get(0).getAccountName(), "accname" );
    assertEquals(accountDetailsList.get(0).getCurrency(), "AUD" );
    assertEquals(accountDetailsList.get(0).getBalance(), "100" );
    }

    @Test
    void testTransactionDetails(){
        Pageable paging = PageRequest.of(1, 10, Sort.by("transdate").descending());
        when(transactionDetailsRepository.findByAccountAccountId(eq(2L),eq(paging))).thenReturn(
                List.of(TransactionDetails.builder()
                        .account(AccountDetails.builder().accountId(12345L).accountName("savings").build())
                        .currency("AUD")
                        .creditAmount("20")
                        .debitAmount("0")
                        .transType("credit")
                        .build())
        );
        List<TransactionDetailsDTO> transactionDetailsList = accountService.getTransactionDetailsList(1, 10, "transdate", 2L);
        assertEquals(transactionDetailsList.get(0).getAccountId(), 12345 );
        assertEquals(transactionDetailsList.get(0).getAccountName(), "savings" );
        assertEquals(transactionDetailsList.get(0).getCurrency(), "AUD" );
        assertEquals(transactionDetailsList.get(0).getCreditAmount(), "20" );
        assertEquals(transactionDetailsList.get(0).getDebitAmount(), "0" );
        assertEquals(transactionDetailsList.get(0).getTransType(), "credit" );
    }
    @Test
    void testTransactionDetailsIdNotFound(){
        Pageable paging = PageRequest.of(1, 10, Sort.by("transdate").descending());
        when(transactionDetailsRepository.findByAccountAccountId(eq(1L),eq(paging))).thenReturn(List.of());
        assertThrows(NoSuchElementException.class, ()->accountService.getTransactionDetailsList(1, 10, "transdate", 1L));
    }
    @Test
    void testAccountDetailsIdNotFound(){
        Pageable paging = PageRequest.of(1, 10);
        when(accountDetailsRepository.findByUserId(eq(2L),eq(paging))).thenReturn(List.of());
        assertThrows(NoSuchElementException.class, ()->accountService.getAccountDetailsList(2L,1,10));
    }

}
