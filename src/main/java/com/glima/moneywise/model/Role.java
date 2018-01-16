package com.glima.moneywise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by Guilherme on 17/01/2018.
 */
@Entity
@Setter @Getter
public class Role {
    
    private Long id;
    private String name;
}
