package com.team.semitravelrecomand.service;


import com.team.semitravelrecomand.dto.request.SaveUserRequest;
import com.team.semitravelrecomand.dto.response.SaveUserResponse;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional
    SaveUserResponse signUp(SaveUserRequest request);
}
