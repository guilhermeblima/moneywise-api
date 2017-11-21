package com.glima.moneywise.resource;

import com.glima.moneywise.model.Person;
import com.glima.moneywise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Guilherme on 21/11/2017.
 */
@RestController
@RequestMapping("/persons")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> listAll(){
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findOne(@PathVariable Long id){
        Person person = personRepository.findOne(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }
}
