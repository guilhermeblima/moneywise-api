package com.glima.moneywise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Guilherme on 20/11/2017.
 */
@Entity
@Getter
@Setter
@Table(name = "tb_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private Boolean active = Boolean.TRUE;

    @Embedded
    @Valid
    private Address address;

    @JsonIgnore
    @Transient
    public Boolean isInactive(){
        return !this.active;
    }
}
