package com.UserExample.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UserExample.UserService.entity.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
   Optional<AppUser> findByUserId(Long userId);
}
