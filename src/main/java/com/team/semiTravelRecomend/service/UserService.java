package com.team.semiTravelRecomend.service;

import com.team.semiTravelRecomend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecomend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecomend.model.dto.response.LoginUserResponse;

public interface UserService {

    void save(SaveUserRequest request);

    LoginUserResponse login(LoginUserRequest request);
}
