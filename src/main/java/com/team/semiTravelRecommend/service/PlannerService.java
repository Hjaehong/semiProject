package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.record.PlannerDTO;

public interface PlannerService {

    int insertPlanner(PlannerDTO planner);

    PlannerDTO plannerOne(int planNo);

    int editPlanner(PlannerDTO planner);

    int deletePlanner(int planNo);
}
