package com.glima.moneywise.repository.transaction;

import com.glima.moneywise.model.Transaction;
import com.glima.moneywise.repository.filter.TransactionFilter;
import com.glima.moneywise.repository.projection.TransactionSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Guilherme on 05/12/2017.
 */
public interface TransactionRepositoryQuery {

    Page<Transaction> findByFilter(TransactionFilter transactionFilter, Pageable pageable);
    Page<TransactionSummary> findTransactionSummary(TransactionFilter transactionFilter, Pageable pageable);
}
