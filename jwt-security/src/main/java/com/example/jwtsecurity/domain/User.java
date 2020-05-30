package com.example.jwtsecurity.domain;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    private Integer age;

    private String address;

    private Integer roleId;

}
