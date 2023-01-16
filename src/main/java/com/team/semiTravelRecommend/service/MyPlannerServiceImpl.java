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

    @Override
    public List<PlannerDTO> findPlannerTitle(int userNo) {
        return myPlannerMapper.findPlannerTitle(userNo);
    }

    @Override
    public PlannerDTO findPlannerNo(int planNo) { return myPlannerMapper.findPlannerNo(planNo); }

    @Override
    public int deletePlanner(int planNo) { return myPlannerMapper.deletePlanner(planNo); }

    @Override
    public int showUpdatePlanner(int planNo) { return myPlannerMapper.showUpdatePlanner(planNo); }

/*    @Override
    public int updatePlanner(PlannerDTO planner) {
        int result = myPlannerMapper.updatePlanner(planner);

        return result;
    }*/

    public int updatePlanner(int planNo) { int result = myPlannerMapper.updatePlanner(planNo);
        System.out.println(result);
        return result;
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

/*    @Override
    public List<PlannerDTO> findPlannerTitle(int userCode) {

        return myPlannerMapper.findPlannerTitle(userCode);
    }*/
}
