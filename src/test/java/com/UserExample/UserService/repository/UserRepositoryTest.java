package com.UserExample.UserService.repository;

import com.UserExample.UserService.entity.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindUserInfoByUserId(){
        //given
        AppUser mockAppUser=getMockAppUser();
        //when
        AppUser appUser= userRepository.save(getMockAppUser());
        mockAppUser.setUserId(1L);
        //then
        Optional<AppUser> appUserReturned = userRepository.findByUserId(1L);

    }

    private AppUser getMockAppUser() {
        AppUser appUser=new AppUser();
        appUser.setUserId(1234L);
        appUser.setName("Kent");
        appUser.setAge(32);
        appUser.setCreatedAt(Date.from(Instant.now()));
        appUser.setUpdatedAt(Date.from(Instant.now()));
        return appUser;
    }

}
