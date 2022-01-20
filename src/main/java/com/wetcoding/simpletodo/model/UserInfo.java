package com.wetcoding.simpletodo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UserInfo extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
}
