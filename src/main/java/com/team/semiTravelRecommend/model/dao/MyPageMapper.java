package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.record.UserTagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    UserTagDTO readUserInfo();
}
