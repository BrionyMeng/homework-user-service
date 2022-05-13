package com.UserExample.UserService.service;

import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public GetUserInfoResponse getUserById(String userId) {
        return new GetUserInfoResponse("2334","Bruce",44);
    }
}
