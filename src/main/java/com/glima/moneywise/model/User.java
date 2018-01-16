package com.glima.moneywise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Guilherme on 17/01/2018.
 */
@Entity
@Getter @Setter
@Table(name = "tb_user")
public class User {

    @Id
    private Long id;
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "id_user"),
    inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;

}
