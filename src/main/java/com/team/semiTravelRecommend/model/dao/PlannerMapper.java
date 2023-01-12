package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.record.PlannerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlannerMapper {

    int insertPlanner(PlannerDTO planner);
}
