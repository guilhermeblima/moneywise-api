package com.glima.moneywise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Guilherme on 17/01/2018.
 */
@Entity
@Getter @Setter
public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private List<Role> roles;

}
