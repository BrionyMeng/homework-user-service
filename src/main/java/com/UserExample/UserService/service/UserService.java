package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import com.UserExample.UserService.web.dto.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public GetUserInfoResponse getUserById(Long userId) {
        Optional<AppUser> appUser = userRepository.findById(userId);
        if (appUser.isPresent()) {
            AppUser appUserReturned = appUser.get();
            return new GetUserInfoResponse(appUserReturned.getId(), appUserReturned.getName(), appUserReturned.getAge());
        }
        return new GetUserInfoResponse();
    }

    public AppUser createUser(UserInfo userInfo) {
        AppUser appUser = new AppUser(userInfo.getName(), userInfo.getAge());
        return userRepository.save(appUser);
    }

    public List<GetUserInfoResponse> getUserList() {
        List<AppUser> appUserList = userRepository.findAll();
        List<GetUserInfoResponse> getUserInfoResponseList = new ArrayList<>();
        appUserList.forEach(user ->
                getUserInfoResponseList.add(
                        new GetUserInfoResponse(
                                user.getId(),
                                user.getName(),
                                user.getAge()
                        )));
        return getUserInfoResponseList;
    }
}
