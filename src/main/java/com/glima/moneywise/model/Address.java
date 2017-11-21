package com.glima.moneywise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Created by Guilherme on 20/11/2017.
 */
@Embeddable
@Getter @Setter
public class Address {

    @Size(max = 10)
    private String number;

    @Size(max = 30)
    private String street;

    @Size(max = 10)
    private String zipCode;

    @Size(max = 20)
    private String suburb;

    @Size(max = 20)
    private String city;
}
