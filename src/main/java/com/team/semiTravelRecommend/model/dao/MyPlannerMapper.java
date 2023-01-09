package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPlannerMapper {

    //String findPlannerByCode();

    List<PlannerDTO> findPlannerByCode(int code);

    List<PlannerDTO> findPlannerTitle(int userCode);

}
