package com.glima.moneywise.repository.transaction;

import com.glima.moneywise.model.Transaction;
import com.glima.moneywise.repository.filter.TransactionFilter;

import java.util.List;

/**
 * Created by Guilherme on 05/12/2017.
 */
public interface TransactionRepositoryQuery {

    List<Transaction> findByFilter(TransactionFilter transactionFilter);
}
