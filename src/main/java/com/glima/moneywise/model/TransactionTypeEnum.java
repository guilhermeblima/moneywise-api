package com.glima.moneywise.model;

import lombok.Getter;

/**
 * Created by Guilherme on 01/12/2017.
 */
@Getter
public enum TransactionTypeEnum {

    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    TransactionTypeEnum(String value) {
        this.value = value;
    }

    private String value;
}
