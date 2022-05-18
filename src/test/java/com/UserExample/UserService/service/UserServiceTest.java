package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import com.UserExample.UserService.web.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.ZonedDateTime;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


public class UserServiceTest {


    UserRepository userRepository = mock(UserRepository.class);

    UserService userService = new UserService(userRepository);


    @Test
    public void shouldReturnUserInfo() {
        //given
        Long userId = 123L;
        AppUser appUserExpected = new AppUser(123L, "Bruce", 32, ZonedDateTime.now(), ZonedDateTime.now());
        GetUserInfoResponse userInfoResponseExpected = new GetUserInfoResponse(123L, "Bruce", 32);

        //when
        Mockito.when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(appUserExpected));
        GetUserInfoResponse getUserInfoResponse = userService.getUserById(userId);
        //then
        assertEquals(getUserInfoResponse.getUserId(), userInfoResponseExpected.getUserId());
        assertEquals(getUserInfoResponse.getName(), userInfoResponseExpected.getName());
        assertEquals(getUserInfoResponse.getAge(), userInfoResponseExpected.getAge());
    }

    @Test
    public void shouldReturnSuccessCreateUserResponse() {
        //given
        UserInfo userInfo = new UserInfo("Lector", 45);
        AppUser appUserExpected = new AppUser(
                1234L, "Lector", 45, ZonedDateTime.now(), ZonedDateTime.now()
        );

        //when
        Mockito.when(userRepository.save(any(AppUser.class))).thenReturn(appUserExpected);

        AppUser actualAppUser=userService.createUser(userInfo);

        //then
        assertEquals(appUserExpected.getId(),actualAppUser.getId());
        assertEquals(appUserExpected.getName(),actualAppUser.getName());
        assertEquals(appUserExpected.getAge(),actualAppUser.getAge());
        assertEquals(appUserExpected.getCreatedAt(),actualAppUser.getCreatedAt());
        assertEquals(appUserExpected.getUpdatedAt(),actualAppUser.getUpdatedAt());
    }
}
