package com.glima.moneywise.resource;

import com.glima.moneywise.event.CreatedResourceEvent;
import com.glima.moneywise.model.Transaction;
import com.glima.moneywise.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Guilherme on 01/12/2017.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionResource {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

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

    @PostMapping
    public ResponseEntity<Transaction> save(@Valid @RequestBody Transaction transaction, HttpServletResponse response){
        transaction = transactionRepository.save(transaction);
        publisher.publishEvent(new CreatedResourceEvent(this,response,transaction.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
