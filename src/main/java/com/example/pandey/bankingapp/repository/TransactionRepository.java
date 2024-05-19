package com.example.pandey.bankingapp.repository;

import com.example.pandey.bankingapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByAccountIdOrderByTimeStampDesc(Long accountId);
}
