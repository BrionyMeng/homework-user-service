package com.UserExample.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser{
    private String userId;
    private String name;
    private int age;
    private Date createdAt;
    private Date updatedAt;
}
