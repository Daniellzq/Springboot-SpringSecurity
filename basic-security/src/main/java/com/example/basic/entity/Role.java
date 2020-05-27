package com.example.basic.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private String description;
}
