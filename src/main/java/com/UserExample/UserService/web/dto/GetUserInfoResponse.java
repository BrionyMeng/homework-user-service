package com.UserExample.UserService.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoResponse {
    private String userId;
    private String name;
    private int age;
}
