package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.UserVO;
import com.team.semiTravelRecommend.model.dto.requset.UpdateUserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(UserVO userVO);

    void update(UserVO userVO);

    void delete(UserVO userVO);

    Optional<UserVO> findByUserId(String userId);

    Optional<UserVO> findByEmail(String email);
}
