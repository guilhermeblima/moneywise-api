package com.glima.moneywise.repository.person;

import com.glima.moneywise.model.Person;
import com.glima.moneywise.repository.filter.PersonFilter;
import com.glima.moneywise.repository.projection.PersonSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 21/04/2018.
 */
public class PersonRepositoryImpl implements PersonRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Person> findByFilter(PersonFilter personFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);

        Predicate[] predicates = filterRestrictions(personFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Person> query = manager.createQuery(criteria);
        addPageableFilters(query, pageable);
        return new PageImpl<>(query.getResultList(),pageable, countElements(personFilter));
    }

    @Override
    public Page<PersonSummary> findByFilterSummary(PersonFilter personFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PersonSummary> criteria = builder.createQuery(PersonSummary.class);
        Root<Person> root = criteria.from(Person.class);

        criteria.select(builder.construct(PersonSummary.class,
                root.get("id"), root.get("name"), root.get("address").get("city"), root.get("address").get("suburb"),
                root.get("active") ));

        Predicate[] predicates = filterRestrictions(personFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<PersonSummary> query = manager.createQuery(criteria);
        addPageableFilters(query, pageable);
        return new PageImpl<>(query.getResultList(),pageable, countElements(personFilter));
    }


    private void addPageableFilters(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int firstPageElement = currentPage * pageSize;

        query.setFirstResult(firstPageElement);
        query.setMaxResults(pageSize);
    }

    private Long countElements(PersonFilter personFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Person> root = criteria.from(Person.class);

        Predicate[] predicates = filterRestrictions(personFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();

    }


    private Predicate[] filterRestrictions(PersonFilter personFilter, CriteriaBuilder builder, Root<Person> root) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(personFilter.getName())){
            predicates.add(builder.like(
                    builder.lower(root.get("name")), "%" + personFilter.getName().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
