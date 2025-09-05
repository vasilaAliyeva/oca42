package com.example.oca42.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private Integer age;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses; //open in view

    @OneToOne(mappedBy = "user") //relation contact tablede saxlanir
    private Contact contact;

    //OneToOne -> id ler her iki terefde saxlanila biler
    //OneToMany -> id many terefde saxlanilir
    //ManyToOne -> id many terefde saxlanilir
    //ManyToMany -> id ler 3 cu table da saxlanilir

}
