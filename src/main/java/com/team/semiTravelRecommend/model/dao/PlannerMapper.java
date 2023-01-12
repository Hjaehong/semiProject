package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlannerMapper {

    int insertPlanner(PlannerDTO plannerDTO);

    List<PlannerDTO> findAllPlanner();

}
