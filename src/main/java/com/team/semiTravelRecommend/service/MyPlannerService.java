package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;

import java.util.List;

public interface MyPlannerService {


    //String findPlannerByCode();

    //List<PlannerDTO> findPlannerByCode(int code);

    //List<PlannerDTO> findPlannerTitle(int userCode);

/*    List<PlannerDTO> findPlannerTitle(int userCode);*/

    List<PlannerDTO> findPlannerTitle(int userNo);

    PlannerDTO findPlannerNo(int planNo);

    int deletePlanner(int planNo);

    int showUpdatePlanner(int planNo);

    int updatePlanner(int planNo);

    void updatePlanner(PlannerDTO plannerDTO);
}
