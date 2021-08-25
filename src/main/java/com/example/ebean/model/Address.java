package com.example.ebean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Address extends BaseModel {

    private String addressLine1;
    private String addressLine2;
    private String city;
}
