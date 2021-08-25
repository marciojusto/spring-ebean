package com.example.ebean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Customer extends BaseModel {

    private String name;

    private String identifier;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
