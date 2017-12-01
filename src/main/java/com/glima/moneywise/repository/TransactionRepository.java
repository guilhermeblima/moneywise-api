package com.glima.moneywise.repository;

import com.glima.moneywise.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Guilherme on 01/12/2017.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
