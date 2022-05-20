package com.UserExample.UserService.service;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.repository.UserRepository;
import com.UserExample.UserService.web.dto.Criteria;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import com.UserExample.UserService.web.dto.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public List<GetUserInfoResponse> getUserListByPage(Integer pageNo, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Slice<AppUser> appUserSlice = userRepository.findAll(pageable);
        List<GetUserInfoResponse> getUserInfoResponseList = new ArrayList<>();
        appUserSlice.forEach(user ->
                getUserInfoResponseList.add(
                        new GetUserInfoResponse(
                                user.getId(),
                                user.getName(),
                                user.getAge()
                        )));
        return getUserInfoResponseList;
    }

    public List<GetUserInfoResponse> getUserListWithCriteria(Criteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageInfo().getPageNo(), criteria.getPageInfo().getPageSize());
        Slice<AppUser> appUserSlice = userRepository.findByNameLikeAndAgeBetween(criteria.getName(), criteria.getMaxAge(), criteria.getMinAge(), pageable);
        List<GetUserInfoResponse> getUserInfoResponseList = new ArrayList<>();
        appUserSlice.forEach(user ->
                getUserInfoResponseList.add(
                        new GetUserInfoResponse(
                                user.getId(),
                                user.getName(),
                                user.getAge()
                        )));
        return getUserInfoResponseList;
    }

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


    /*
    *
    * 以下注释为使用Specification的例子
    * 留作参考
    *
    */

//    public List<GetUserInfoResponse> appUserQuery(Integer pageNo, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//
//        Specification<AppUser> spec = (root, query, criteriaBuilder) -> {
//            Path<AppUser> path = root.get("name");
//            return criteriaBuilder.equal(path, "Kent");
//        };
//
//        Slice<AppUser> appUserSlice = userRepository.findAll(spec, pageable);
//        List<GetUserInfoResponse> getUserInfoResponseList = new ArrayList<>();
//        appUserSlice.forEach(user ->
//                getUserInfoResponseList.add(
//                        new GetUserInfoResponse(
//                                user.getId(),
//                                user.getName(),
//                                user.getAge()
//                        )));
//        return getUserInfoResponseList;
//    }


}
