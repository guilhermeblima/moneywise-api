package com.glima.moneywise.resource;

import com.glima.moneywise.model.Transaction;
import com.glima.moneywise.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Guilherme on 01/12/2017.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionResource {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> listAll(){
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findOne(@PathVariable Long id){
        Transaction transaction = transactionRepository.findOne(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }
}
