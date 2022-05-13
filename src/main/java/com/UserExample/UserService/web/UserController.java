package com.UserExample.UserService.web;

import com.UserExample.UserService.service.UserService;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserInfoResponse getUserInfoResponse(@PathVariable String userId){
        return userService.getUserById(userId);
    }
}
