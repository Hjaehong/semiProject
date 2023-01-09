package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.PlannerMapper;
import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannerServiceImpl implements PlannerService{

    private final PlannerMapper plannerMapper;

    @Autowired
    public PlannerServiceImpl(PlannerMapper plannerMapper) { this.plannerMapper = plannerMapper; }


    @Override
    public boolean insertPlanner(PlannerDTO plannerDTO) throws Exception {
        int result = plannerMapper.insertPlanner(plannerDTO);



        if(result <= 0) {
            throw new Exception("플래너등록실패");
        }
        return result > 0 ? true : false;

    }

    @Override
    public List<PlannerDTO> findAllPlanner() {
        return plannerMapper.findAllPlanner();
    }

    @Override
    public boolean deletePlanner(PlannerDTO plannerDTO) {
        int result = plannerMapper.deletePlanner(plannerDTO);

        if(result <= 0) {

        }
        return result > 0 ? true : false;
    }
}
