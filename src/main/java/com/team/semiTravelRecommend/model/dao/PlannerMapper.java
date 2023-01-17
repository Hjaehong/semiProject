package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlannerMapper {

    int insertPlanner(PlannerDTO planner);

    PlannerDTO plannerOne(int planNo);

    int editPlanner(PlannerDTO planner);

    int deletePlanner(int planNo);
}
