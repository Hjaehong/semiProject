package com.team.semiTravelRecommend.service;


import com.team.semiTravelRecommend.model.dto.PlannerDTO;

import java.util.List;

public interface PlannerService {

    // 스트링이 아니라 인트나 불린으로 받는다
    boolean insertPlanner(PlannerDTO plannerDTO) throws Exception;

    List<PlannerDTO> findAllPlanner();

    boolean deletePlanner(PlannerDTO plannerDTO);
}
