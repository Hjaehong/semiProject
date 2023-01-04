package com.team.semiTravelRecomend.service;


import com.team.semiTravelRecomend.model.dao.UserMapper;
import com.team.semiTravelRecomend.model.dto.User;
import com.team.semiTravelRecomend.model.dto.UserRole;
import com.team.semiTravelRecomend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecomend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecomend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecomend.model.dto.response.DeleteUserResponse;
import com.team.semiTravelRecomend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecomend.model.dto.response.SaveUserResponse;
import com.team.semiTravelRecomend.model.dto.response.UpdateUserResponse;
import lombok.RequiredArgsConstructor;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;


    @Override
    @Transactional
    public SaveUserResponse save(SaveUserRequest saveUserRequest) {
        User user = User.builder()
                .userId(saveUserRequest.getUserId())
                .userPwd(BCrypt.hashpw(saveUserRequest.getUserPwd(), BCrypt.gensalt()))
                .email(saveUserRequest.getEmail())
                .userName(saveUserRequest.getUserName())
                .role(UserRole.USER)
                .build();

        User savedUser = userMapper.save(user);

        SaveUserResponse saveUserResponse = new SaveUserResponse(savedUser);

        return saveUserResponse;
    }

    @Override
    @Transactional
    public UpdateUserResponse update(Long userNo, UpdateUserRequest updateUserRequest) {
        User updatedUser = userMapper.findById(userNo).orElseThrow(IllegalArgumentException::new);
        updatedUser.updateUser(updateUserRequest.getUserName(), updateUserRequest.getEmail(), updateUserRequest.getUserPwd());
        userMapper.update(updatedUser);

        return new UpdateUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public DeleteUserResponse delete(Long userNo) {
        User deletedUser = userMapper.findById(userNo).orElseThrow(IllegalArgumentException::new);
        userMapper.delete(deletedUser.getUserNo());
        return new DeleteUserResponse(deletedUser);
    }

    @Override
    public List<SaveUserResponse> findUsers() {
        List<User> Users = userMapper.findAll();

        List<SaveUserResponse> list = new ArrayList<>();

        for (User user : Users) {
            SaveUserResponse saveUserResponse = new SaveUserResponse(user);
            list.add(saveUserResponse);
        }

        return list;

    }

    @Override
    public SaveUserResponse findUser(Long userNo) {
        User user = userMapper.findById(userNo).orElseThrow(IllegalArgumentException::new);

        SaveUserResponse saveUserResponse = new SaveUserResponse(user);

        return saveUserResponse;
    }


    public LoginUserResponse login(LoginUserRequest loginUserRequest) {
        User user = userMapper.login(loginUserRequest.getUserId()).orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호를 확인해주세요."));

        if (!BCrypt.checkpw(loginUserRequest.getPassword(), user.getUserPwd())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호를 확인해주세요.");
        }

        return new LoginUserResponse(user);
    }
}


















