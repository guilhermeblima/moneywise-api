package com.glima.moneywise.service;

import com.glima.moneywise.model.Person;
import com.glima.moneywise.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created by Guilherme on 30/11/2017.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Long id, Person person){
        Person updatedPerson = findPersonById(id);
        BeanUtils.copyProperties(person, updatedPerson, "id");
        return personRepository.save(updatedPerson);
    }

    public void updateStatusProperty(Long id, Boolean status) {
        Person person = findPersonById(id);
        person.setActive(status);
        personRepository.save(person);
    }

    public Person findPersonById(Long id){
        Person person = personRepository.findOne(id);
        if(person == null){
            throw new EmptyResultDataAccessException(1);
        }
        return person;
    }
}
