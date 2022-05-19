package com.UserExample.UserService.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UserExample.UserService.entity.AppUser;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

//    Slice<AppUser> findALl(Pageable pageable);
}
