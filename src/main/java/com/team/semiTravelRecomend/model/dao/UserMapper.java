package com.team.semiTravelRecomend.model.dao;

import com.team.semiTravelRecomend.model.dto.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(UserVO userVO);

    UserVO update(UserVO userVO);

    UserVO delete(Long userNo);

    List<UserVO> findAll();

    Optional<UserVO> findById(Long userNo);

    Optional<UserVO> findByUserId(String userId);

    Optional<UserVO> login(String userId);
}
