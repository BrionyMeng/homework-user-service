package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public GetUserInfoResponse getUserById(Long userId) {
        Optional<AppUser> appUser = userRepository.findById(userId);
        if(appUser.isPresent()) {
            AppUser appUserReturned = appUser.get();
            return new GetUserInfoResponse(appUserReturned.getId(), appUserReturned.getName(), appUserReturned.getAge());
        }
        return new GetUserInfoResponse();
    }
}
