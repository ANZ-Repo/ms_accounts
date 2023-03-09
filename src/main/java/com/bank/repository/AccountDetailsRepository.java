package com.bank.repository;

import com.bank.repository.entity.AccountDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
    List<AccountDetails> findByUserId(Long userId, Pageable pageable);
}
