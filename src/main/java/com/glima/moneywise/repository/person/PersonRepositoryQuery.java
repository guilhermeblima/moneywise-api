package com.glima.moneywise.repository.person;

import com.glima.moneywise.model.Person;
import com.glima.moneywise.repository.filter.PersonFilter;
import com.glima.moneywise.repository.projection.PersonSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Guilherme on 21/04/2018.
 */
public interface PersonRepositoryQuery {

    Page<Person> findByFilter(PersonFilter personFilter, Pageable pageable);
    Page<PersonSummary> findByFilterSummary(PersonFilter personFilter, Pageable pageable);
}
