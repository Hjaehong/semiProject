package com.team.semiTravelRecommend.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlannerMapper {

    int registPlanner(PlannerDTO newPlanner);
}
