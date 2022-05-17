package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public GetUserInfoResponse getUserById(String userId) {
        AppUser appUser = userRepository.findByUserId(userId);
        GetUserInfoResponse getUserInfoResponse = new GetUserInfoResponse(appUser.getUserId(), appUser.getName(), appUser.getAge());
        return getUserInfoResponse;
    }
}
