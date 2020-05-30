package com.example.jwtsecurity.domain;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private String description;
}
