package com.UserExample.UserService.repository;

import com.UserExample.UserService.entity.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
        return appUser;
    }
}
