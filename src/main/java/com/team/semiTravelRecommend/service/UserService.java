package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.UserResponse;
import com.team.semiTravelRecommend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.model.dto.response.UpdateUserResponse;

public interface UserService {

    void save(SaveUserRequest request);

    LoginUserResponse login(LoginUserRequest request);

    UserResponse getUser(String userId);

    void update(UpdateUserRequest request, UserResponse user);

    void delete(UpdateUserRequest request, UserResponse attribute);
}
