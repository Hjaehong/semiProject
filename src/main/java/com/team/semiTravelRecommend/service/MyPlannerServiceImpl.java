package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.MyPlannerMapper;
import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPlannerServiceImpl implements MyPlannerService{

    private final MyPlannerMapper myPlannerMapper;

    public MyPlannerServiceImpl(MyPlannerMapper myPlannerMapper) {
        this.myPlannerMapper = myPlannerMapper;
    }

    /*@Override
    public String findPlannerByCode() {
        return myPlannerMapper.findPlannerByCode();
    }*/




/*    @Override
    public List<PlannerDTO> findPlannerByCode(int code) {
        return myPlannerMapper.findPlannerByCode(code);
    }

    @Override
    public List<PlannerDTO> findPlannerTitle(int userCode) { return myPlannerMapper.findPlannerTitle(userCode); }*/

    @Override
    public List<PlannerDTO> findPlannerTitle(int userCode) {

        return myPlannerMapper.findPlannerTitle(userCode);
    }
}
