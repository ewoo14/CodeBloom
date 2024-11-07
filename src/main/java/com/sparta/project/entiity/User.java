package com.sparta.project.entiity;

import jakarta.persistence.*;

@Entity
@Table(name = "p_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
