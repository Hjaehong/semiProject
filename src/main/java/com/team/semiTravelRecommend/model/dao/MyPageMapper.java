package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.BookmarkDTO;
import com.team.semiTravelRecommend.model.dto.CityDTO;
import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import com.team.semiTravelRecommend.model.dto.RecordDTO;
import com.team.semiTravelRecommend.model.dto.UserTagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    UserTagDTO readUserInfo(int loginUserNo);

    List<CityDTO> readMyBadge(int loginUserNo);

    List<RecordDTO> readMyRecord(int loginUserNo);

    List<RecordDTO> readMyHeart(int loginUserNo);

    List<PlannerDTO> readMyPlanner(int loginUserNo);

    List<BookmarkDTO> readMyBookMark(int loginUserNo);
}
