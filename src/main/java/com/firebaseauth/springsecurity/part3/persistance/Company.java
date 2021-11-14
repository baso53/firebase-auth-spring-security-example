package com.firebaseauth.springsecurity.part3.persistance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Company {

    @Id
    private Long id;

    private String name;

    private String type;
}
