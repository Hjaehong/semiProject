package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.record.PlannerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPlannerMapper {

    List<PlannerDTO> findPlannerTitle(int userNo);

    PlannerDTO findPlannerNo(int planNo);

    int deletePlanner(int planNo);

    int showUpdatePlanner(int planNo);

    int updatePlanner(int planNo);




    //int deletePlanner(PlannerDTO plannerDTO);
    //String findPlannerByCode();

/*    List<PlannerDTO> findPlannerByCode(int code);

    List<PlannerDTO> findPlannerTitle(int userCode);*/

}
