package com.glima.moneywise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Guilherme on 17/01/2018.
 */
@Entity
@Setter @Getter
@Table(name = "tb_role")
public class Role {

    @Id
    private Long id;
    private String name;
}
