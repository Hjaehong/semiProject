package com.team.semiTravelRecomend.service;


import com.team.semiTravelRecomend.model.dto.User;
import com.team.semiTravelRecomend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecomend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecomend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecomend.model.dto.response.DeleteUserResponse;
import com.team.semiTravelRecomend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecomend.model.dto.response.SaveUserResponse;
import com.team.semiTravelRecomend.model.dto.response.UpdateUserResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    @Transactional
    void save(User user);

    @Transactional
    UpdateUserResponse update(Long userNo, UpdateUserRequest updateUserRequest);

    @Transactional
    DeleteUserResponse delete(Long userNo);

    List<SaveUserResponse> findUsers();

    SaveUserResponse findUser(Long userNo);

    LoginUserResponse login(LoginUserRequest loginUserRequest);
}

