package com.UserExample.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser{
    private String userId;
    private String name;
    private int age;
    private Date createdAt;
    private Date updatedAt;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    public String getUserId() {
        return userId;
    }
}
