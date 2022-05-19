package com.UserExample.UserService.web;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.service.UserService;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import com.UserExample.UserService.web.dto.PageInfo;
import com.UserExample.UserService.web.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetUserInfoResponse> getUserInfoResponsesList(){
        return userService.getUserList();
    }

    @GetMapping(path = "/page")
    @ResponseStatus(HttpStatus.OK)
    public List<GetUserInfoResponse> getUserInfoResponsesListByPage(@RequestBody PageInfo pageInfo){
        int pageNo= pageInfo.getPageNo();
        int pageSize=pageInfo.getPageSize();

        return userService.getUserListByPage(pageNo,pageSize);
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserInfoResponse getUserInfoResponse(@PathVariable String userId){
        return userService.getUserById(Long.parseLong(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserInfo userInfo){
        AppUser appUser=userService.createUser(userInfo);
        return appUser.getId();
    }
}
