package com.glima.moneywise.repository.transaction;

import com.glima.moneywise.model.Transaction;
import com.glima.moneywise.repository.filter.TransactionFilter;
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
 * Created by Guilherme on 05/12/2017.
 */
public class TransactionRepositoryImpl implements TransactionRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Transaction> findByFilter(TransactionFilter transactionFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);
        Root<Transaction> root = criteria.from(Transaction.class);

        Predicate[] predicates = filterRestrictions(transactionFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Transaction> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] filterRestrictions(TransactionFilter transactionFilter, CriteriaBuilder builder, Root<Transaction> root) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(transactionFilter.getTitle())){
            predicates.add(builder.like(
                    builder.lower(root.get("title")), "%" + transactionFilter.getTitle().toLowerCase() + "%"));
        }

        if(transactionFilter.getDateFrom() != null){
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("date"), transactionFilter.getDateFrom())
            );
        }

        if(transactionFilter.getDateTo() != null){
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("date"), transactionFilter.getDateTo())
            );
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
