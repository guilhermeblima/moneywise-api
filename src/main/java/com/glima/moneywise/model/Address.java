package com.glima.moneywise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Created by Guilherme on 20/11/2017.
 */
@Embeddable
@Getter @Setter
public class Address {

    private String number;
    private String street;
    private String zipCode;
    private String suburb;
    private String city;
}
