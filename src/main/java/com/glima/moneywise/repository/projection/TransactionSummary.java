package com.glima.moneywise.repository.projection;

import com.glima.moneywise.model.TransactionTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Guilherme on 17/01/2018.
 */
@Getter @Setter
public class TransactionSummary {

    public TransactionSummary(Long id, String title, LocalDate date, LocalDate paymentDue, BigDecimal total,
                              TransactionTypeEnum type, String category, String person) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.paymentDue = paymentDue;
        this.total = total;
        this.type = type;
        this.category = category;
        this.person = person;
    }

    private Long id;
    private String title;
    private LocalDate date;
    private LocalDate paymentDue;
    private BigDecimal total;
    private TransactionTypeEnum type;
    private String category;
    private String person;
}
