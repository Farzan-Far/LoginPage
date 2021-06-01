package com.example.demo3.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "this Shoulde be filled")
    @NotEmpty(message = "this Shoulde be filled")
    private String name;
    @NotNull(message = "this Shoulde be filled")
    @NotEmpty(message = "this Shoulde be filled")
    private String family;
    @Id
    private String email;
    @NotNull(message = "this Shoulde be filled")
    @NotEmpty(message = "this Shoulde be filled")
    private String phone;
    @NotNull(message = "this Shoulde be filled")
    @NotEmpty(message = "this Shoulde be filled")
    private String password;
}
