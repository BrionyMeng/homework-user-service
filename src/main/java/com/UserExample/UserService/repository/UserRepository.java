package com.UserExample.UserService.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.UserExample.UserService.entity.AppUser;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

//    Slice<AppUser> findAll(Specification spec, Pageable pageable);

    @Query(value = "SELECT *\n" +
            "FROM app_user\n" +
            "WHERE age <= ?2\n" +
            "AND age >= ?3\n" +
            "AND name like ?1", nativeQuery = true)
    Slice<AppUser> findByNameLikeAndAgeBetween(String name, int maxAge, int minAge, Pageable pageable);
}
