package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public GetUserInfoResponse getUserById(Long userId) {
        Optional<AppUser> appUser = userRepository.findByUserId(userId);
        if(appUser.isPresent()) {
            AppUser appUserReturned = appUser.get();
            return new GetUserInfoResponse(appUserReturned.getUserId(), appUserReturned.getName(), appUserReturned.getAge());
        }
        return new GetUserInfoResponse();
    }
}
