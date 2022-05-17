package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


public class UserServiceTest {


    UserRepository userRepository = mock(UserRepository.class);

    UserService userService = new UserService(userRepository);


    @Test
    public void shouldReturnUserInfo() {
        //given
        String userId = "user123";
        AppUser appUserExpected = new AppUser("user123", "Bruce", 32, Date.from(Instant.now()), Date.from(Instant.now()));
        GetUserInfoResponse userInfoResponseExpected = new GetUserInfoResponse("user123", "Bruce", 32);

        List<AppUser> expectedUserList = new ArrayList();
        expectedUserList.add(appUserExpected);

        //when
        Mockito.when(userRepository.findByUserId(any(String.class)))
                .thenReturn(appUserExpected);
        GetUserInfoResponse getUserInfoResponse = userService.getUserById(userId);
        //then
        assertEquals(getUserInfoResponse.getUserId(), userInfoResponseExpected.getUserId());
        assertEquals(getUserInfoResponse.getName(), userInfoResponseExpected.getName());
        assertEquals(getUserInfoResponse.getAge(), userInfoResponseExpected.getAge());

    }
}
