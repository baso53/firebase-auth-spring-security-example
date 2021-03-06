package com.firebaseauth.springsecurity.part3.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Subsidiary {

    @Id
    private Long id;

    private String name;

    private String city;

    @ManyToOne
    private Company company;
}
