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

import java.util.HashMap;
 /**
    * Version : 1.0
   * 클래스명: UserServiceImpl
   * 작성일자 : 2023/01/18
 * 작성자 : heojaehong
   * 설명 : 유저 인터페이스 구현 클래스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
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
        userVO.setRegions(request.getRegions());

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
       UserVO user = userMapper.findByUserId(request.getUserId()).orElse(null);

        if (!BCrypt.checkpw(request.getUserPwd(), user.getUserPwd())) {
            return null;
        }
        return new LoginUserResponse(user);
    }

    @Override
    public int findUserNo(String userId){ return userMapper.findUserNo(userId); }

    @Override
    public int insertUserTag(HashMap<String, Object> userTag){
        int result = userMapper.insertUserTag(userTag);

        if(result <= 0) {
            System.out.println("유저 여행 취향 저장 실패");
        }
        return result > 0 ? 1 : 0;
    }
}
