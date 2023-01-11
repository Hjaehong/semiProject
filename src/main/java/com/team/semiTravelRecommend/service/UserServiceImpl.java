package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.UserMapper;
import com.team.semiTravelRecommend.model.dto.UserVO;
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
    public void save(SaveUserRequest request) {

        UserVO userVO = new UserVO();
        userVO.setUserId(request.getUserId());
        userVO.setUserPwd(BCrypt.hashpw(request.getUserPwd(), BCrypt.gensalt()));
        userVO.setUserName(request.getUserName());
        userVO.setEmail(request.getEmail());
        userVO.setNickname(request.getNickname());
        userVO.setRole("USER");

        userMapper.save(userVO);
    }

    public LoginUserResponse login(LoginUserRequest request) {
       UserVO user = userMapper.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호를 확인하세요"));

        if (!BCrypt.checkpw(request.getUserPwd(), user.getUserPwd())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호를 확인하세요");
        }
        return new LoginUserResponse(user);
    }

    @Override
    public void update(Long userNo, UpdateUserRequest updateUserRequest) {
        UserVO updatedUser = userMapper.findById(userNo).orElseThrow(IllegalArgumentException::new);

        updatedUser.updateUser(updateUserRequest.getUserName(), updateUserRequest.getUserPwd(), updateUserRequest.getEmail());


        userMapper.update(updatedUser);
    }

    @Override
    public void delete(Long userNo) {
        UserVO deleteUser = userMapper.findById(userNo).orElseThrow(IllegalArgumentException::new);

        userMapper.delete(deleteUser.getUserNo());
    }


}
