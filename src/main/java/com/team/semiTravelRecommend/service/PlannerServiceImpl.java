package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.PlannerMapper;
import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class PlannerServiceImpl implements PlannerService {

    private final PlannerMapper plannerMapper;

    public PlannerServiceImpl(PlannerMapper plannerMapper) {
        this.plannerMapper = plannerMapper;
    }

    @Override
    public int insertPlanner(PlannerDTO planner) {
        int result = plannerMapper.insertPlanner(planner);

        if(result <= 0) {
            System.out.println("플래너작성실패");
        }
        return result > 0 ? 1 : 0;
    }

    @Override
    public PlannerDTO plannerOne(int planNo) { return plannerMapper.plannerOne(planNo); }

    @Override
    public int editPlanner(PlannerDTO planner) {

        int result = plannerMapper.editPlanner(planner);

        if(result <= 0) {
            System.out.println("플래너작성실패");
        }
        return result > 0 ? 1 : 0;
    }

    @Override
    public int deletePlanner(int planNo) {

        int result = plannerMapper.deletePlanner(planNo);

        if(result <= 0) {
            System.out.println("플래너작성실패");
        }
        return result > 0 ? 1 : 0;
    }
}
