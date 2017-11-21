package com.glima.moneywise.repository;

import com.glima.moneywise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Guilherme on 21/11/2017.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
