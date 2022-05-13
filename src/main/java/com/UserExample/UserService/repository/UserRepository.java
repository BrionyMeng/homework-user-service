package com.UserExample.UserService.repository;

import org.springframework.stereotype.Repository;
import com.UserExample.UserService.entity.AppUser;

import java.util.List;

@Repository
public interface UserRepository {
    List<AppUser> findByUserId(String userId);
}
