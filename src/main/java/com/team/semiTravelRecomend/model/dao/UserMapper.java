package com.team.semiTravelRecomend.model.dao;

import com.team.semiTravelRecomend.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    User save(User user);

    User update(User user);

    User delete(Long userNo);

    List<User> findAll();

    Optional<User> findById(Long userNo);

    Optional<User> login(String userId);
}
