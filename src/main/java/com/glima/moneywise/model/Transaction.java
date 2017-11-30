package com.glima.moneywise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Guilherme on 01/12/2017.
 */
@Entity
@Table(name = "tb_transaction")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Column(name = "dt_transaction")
    private LocalDate date;

    @Column(name = "dt_payment_due")
    private LocalDate paymentDue;

    @NotNull
    private BigDecimal total;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;


}
