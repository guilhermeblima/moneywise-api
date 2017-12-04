package com.glima.moneywise.service;

import com.glima.moneywise.exception.PersonNotFoundOrInactiveException;
import com.glima.moneywise.model.Person;
import com.glima.moneywise.model.Transaction;
import com.glima.moneywise.repository.PersonRepository;
import com.glima.moneywise.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Guilherme on 04/12/2017.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PersonRepository personRepository;


    public Transaction save(Transaction transaction){
        Person person = personRepository.findOne(transaction.getPerson().getId());
        if(person == null || person.isInactive()){
            throw new PersonNotFoundOrInactiveException();
        }
        return transactionRepository.save(transaction);
    }
}
