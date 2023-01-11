package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;

public interface UserService {

    void save(SaveUserRequest request);

    LoginUserResponse login(LoginUserRequest request);

    void update(Long userNo, UpdateUserRequest updateUserRequest);

    void delete(Long userNo);
}
