package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.UserMapper;
import com.team.semiTravelRecommend.model.dto.UserResponse;
import com.team.semiTravelRecommend.model.dto.UserVO;
import com.team.semiTravelRecommend.model.dto.requset.DeleteUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserResponse getUser(String userId) {
        UserVO userVO = userMapper
                .findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        return new UserResponse(userVO);
    }

    @Override
    public void save(SaveUserRequest request) {

        UserVO userVO = new UserVO();
        userVO.setUserId(request.getUserId());
        userVO.setUserPwd(BCrypt.hashpw(request.getUserPwd(), BCrypt.gensalt()));
        userVO.setUserName(request.getUserName());
        userVO.setEmail(request.getEmail());
        userVO.setNickname(request.getNickname());

        userMapper.save(userVO);
    }
    @Override
    public void update(UpdateUserRequest request, UserResponse attribute) {
        UserVO userVO = userMapper.findByUserId(attribute.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

       userVO.updateUser(request.getUserId(), request.getUserName(), request.getEmail());

        userMapper.update(userVO);
    }

    @Override
    public void delete(UserResponse attribute) {
        UserVO userVO = userMapper.findByEmail(attribute.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        userMapper.delete(userVO);
    }

    public LoginUserResponse login(LoginUserRequest request) {
       UserVO user = userMapper.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호를 확인하세요"));

        if (!BCrypt.checkpw(request.getUserPwd(), user.getUserPwd())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호를 확인하세요");
        }
        return new LoginUserResponse(user);
    }
}
