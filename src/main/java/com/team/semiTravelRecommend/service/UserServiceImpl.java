package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.UserMapper;
import com.team.semiTravelRecommend.model.dto.UserVO;
import com.team.semiTravelRecommend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        userVO.setUserPwd(request.getUserPwd());
        userVO.setUserName(request.getUserName());
        userVO.setEmail(request.getEmail());
        userVO.setNickname(request.getNickname());
        userVO.setRole("USER");

        userMapper.save(userVO);
    }

    public LoginUserResponse login(LoginUserRequest request) {
        UserVO userVO = userMapper.findByUserId(request.getUserId())
                .filter(u -> u.getUserPwd().equals(request.getUserPwd()))
                .orElse(null);

        log.info("DB에서 찾아온 데이터 = {}", userVO);

        return new LoginUserResponse(userVO);
    }
}
