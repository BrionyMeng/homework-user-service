package com.UserExample.UserService.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;



@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "app_user")
@EntityListeners(AuditingEntityListener.class)
public class AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private int age;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
