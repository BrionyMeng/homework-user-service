package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.Criteria;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import com.UserExample.UserService.web.dto.PageInfo;
import com.UserExample.UserService.web.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class UserServiceTest {


    UserRepository userRepository = mock(UserRepository.class);

    UserService userService = new UserService(userRepository);


    @Test
    public void shouldReturnUserInfoList() {
        // given
        List<AppUser> appUserListExpected = new ArrayList<>();
        appUserListExpected.add(new AppUser(123L, "Bruce", 32, ZonedDateTime.now(), ZonedDateTime.now()));
        appUserListExpected.add(new AppUser(124L, "Ben", 54, ZonedDateTime.now(), ZonedDateTime.now()));
        appUserListExpected.add(new AppUser(125L, "Jeremy", 74, ZonedDateTime.now(), ZonedDateTime.now()));

        // when
        Mockito.when(userRepository.findAll())
                .thenReturn(appUserListExpected);
        List<GetUserInfoResponse> getUserInfoResponseList = userService.getUserList();

        //then
        assertEquals(appUserListExpected.size(), getUserInfoResponseList.size());
        assertEquals(appUserListExpected.get(0).getName(), getUserInfoResponseList.get(0).getName());
    }

    @Test
    public void shouldReturnUserInfoListByPage() {
        // given
        List<AppUser> appUserListExpected = new ArrayList<>();
        appUserListExpected.add(new AppUser(123L, "Bruce", 32, ZonedDateTime.now(), ZonedDateTime.now()));
        appUserListExpected.add(new AppUser(124L, "Ben", 54, ZonedDateTime.now(), ZonedDateTime.now()));
        appUserListExpected.add(new AppUser(125L, "Jeremy", 74, ZonedDateTime.now(), ZonedDateTime.now()));
        Page<AppUser> appUserPage = new PageImpl<>(appUserListExpected);

        // when
        Mockito.when(userRepository.findAll(PageRequest.of(0, 2)))
                .thenReturn(appUserPage);
        List<GetUserInfoResponse> getUserInfoResponseList = userService.getUserListByPage(0, 2);

        //then
        assertEquals(appUserListExpected.size(), getUserInfoResponseList.size());
        assertEquals(appUserListExpected.get(0).getName(), getUserInfoResponseList.get(0).getName());
    }

    @Test
    public void shouldReturnUserInfoGivenAgeCriteria() {
        // given
        Criteria criteria = new Criteria(new PageInfo(0, 5), 10, 50);
        List<AppUser> appUserListExpected = new ArrayList<>();
        appUserListExpected.add(new AppUser(123L, "Bruce", 32, ZonedDateTime.now(), ZonedDateTime.now()));
        appUserListExpected.add(new AppUser(124L, "Ben", 54, ZonedDateTime.now(), ZonedDateTime.now()));
        Page<AppUser> appUserPage = new PageImpl<>(appUserListExpected);

        // when
        Mockito.when(userRepository.findByNameLikeAndAgeBetween(any(String.class), any(Integer.class), any(Integer.class), any(Pageable.class)))
                .thenReturn(appUserPage);
        List<GetUserInfoResponse> getUserInfoResponseList = userService.getUserListWithCriteria(criteria);

        //then
        assertEquals(appUserListExpected.size(), getUserInfoResponseList.size());
        assertEquals(appUserListExpected.get(0).getName(), getUserInfoResponseList.get(0).getName());
    }

    @Test
    public void shouldReturnUserInfoById() {
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

        AppUser actualAppUser = userService.createUser(userInfo);

        //then
        assertEquals(appUserExpected.getId(), actualAppUser.getId());
        assertEquals(appUserExpected.getName(), actualAppUser.getName());
        assertEquals(appUserExpected.getAge(), actualAppUser.getAge());
        assertEquals(appUserExpected.getCreatedAt(), actualAppUser.getCreatedAt());
        assertEquals(appUserExpected.getUpdatedAt(), actualAppUser.getUpdatedAt());
    }
}
