package com.glima.moneywise.resource;

import com.glima.moneywise.event.CreatedResourceEvent;
import com.glima.moneywise.model.Person;
import com.glima.moneywise.repository.PersonRepository;
import com.glima.moneywise.service.PersonService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Guilherme on 21/11/2017.
 */
@RestController
@RequestMapping("/persons")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> listAll(){
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findOne(@PathVariable Long id){
        Person person = personRepository.findOne(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Person> save(@Valid @RequestBody Person person, HttpServletResponse response){
        person = personRepository.save(person);
        publisher.publishEvent(new CreatedResourceEvent(this, response, person.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        personRepository.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
        return ResponseEntity.ok(personService.update(id,person));
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusPropertie(@PathVariable Long id, @RequestBody Boolean status){
        personService.updateStatusPropertie(id, status);
    }
}
