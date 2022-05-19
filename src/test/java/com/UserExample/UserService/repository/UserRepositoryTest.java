package com.UserExample.UserService.repository;

import com.UserExample.UserService.entity.AppUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateUserWhenCreateUserInfo() {
        // given
        AppUser mockAppUser = getMockAppUser();
        // when
        AppUser appUserSaved = userRepository.save(mockAppUser);
        // then
        assertEquals(mockAppUser.getName(),appUserSaved.getName());
        assertEquals(mockAppUser.getAge(),appUserSaved.getAge());
    }

    @Test
    public void shouldFindUserInfoByUserId() {
        // given
        // when
        AppUser appUser = userRepository.save(getMockAppUser());
        // then
        Optional<AppUser> appUserReturned = userRepository.findById(appUser.getId());
        assertEquals(appUser, appUserReturned.get());

    }

    private AppUser getMockAppUser() {
        AppUser appUser = new AppUser();
        appUser.setName("Kent");
        appUser.setAge(32);
        appUser.setCreatedAt(ZonedDateTime.parse("2022-05-18T16:38:12.267383+08:00[Asia/Shanghai]"));
        appUser.setUpdatedAt(ZonedDateTime.parse("2022-05-18T16:38:12.267383+08:00[Asia/Shanghai]"));
        return appUser;
    }


}
