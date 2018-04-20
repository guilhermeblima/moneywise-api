package com.glima.moneywise.repository.projection;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Guilherme on 21/04/2018.
 */
@Getter @Setter
public class PersonSummary {

    public PersonSummary(Long id, String name, String city, String suburb, boolean active) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.suburb = suburb;
        this.active = active;
    }

    private Long id;
    private String name;
    private String city;
    private String suburb;
    private boolean active;
}
