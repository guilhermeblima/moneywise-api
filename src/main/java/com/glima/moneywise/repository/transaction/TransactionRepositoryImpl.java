package com.glima.moneywise.repository.transaction;

import com.glima.moneywise.model.Transaction;
import com.glima.moneywise.repository.filter.TransactionFilter;
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
 * Created by Guilherme on 05/12/2017.
 */
public class TransactionRepositoryImpl implements TransactionRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Transaction> findByFilter(TransactionFilter transactionFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);
        Root<Transaction> root = criteria.from(Transaction.class);

        Predicate[] predicates = filterRestrictions(transactionFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Transaction> query = manager.createQuery(criteria);
        addPageableFilters(query, pageable);
        return new PageImpl<>(query.getResultList(),pageable, countElements(transactionFilter));
    }



    private void addPageableFilters(TypedQuery<Transaction> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int firstPageElement = currentPage * pageSize;

        query.setFirstResult(firstPageElement);
        query.setMaxResults(pageSize);
    }

    private Long countElements(TransactionFilter transactionFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Transaction> root = criteria.from(Transaction.class);

        Predicate[] predicates = filterRestrictions(transactionFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();

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
