package com.example.oca42.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //toMany olanlarda hamsi  default olaraq lazy olur
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses; //open in view

    //toOne relationlarda default olaraq eagerdi
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER) //relation contact tablede saxlanir
    private Contact contact;

    //MANY TO MANY USERDE YAZIRIQ relationu
    //role tablelerde esasen eager qoyulur many to many olmasina baxmayaraq
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    //OneToOne -> id ler her iki terefde saxlanila biler
    //OneToMany -> id many terefde saxlanilir
    //ManyToOne -> id many terefde saxlanilir
    //ManyToMany -> id ler 3 cu table da saxlanilir

}
