package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import com.team.semiTravelRecommend.service.MyPlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPlannerController {

    private final MyPlannerService myPlannerService;

    public MyPlannerController(MyPlannerService myPlannerService) {
        this.myPlannerService = myPlannerService;
    }

//    @GetMapping("/myPlanner")
//    public String Hello() {
//        return "/mypage/myPlanner";
//    }

    @GetMapping("/myPlanner")
    public ModelAndView findPlannerTitle(ModelAndView mv) {

        List<PlannerDTO> plannerList = myPlannerService.findPlannerTitle();
        mv.addObject("plannerList", plannerList);
        mv.setViewName("mypage/myPlanner");

        return mv;
    }

    @GetMapping("myPlannerDetail")
    public void findPlannerNo(Model model) {

        List<PlannerDTO> plannerList = myPlannerService.findPlannerNo();
        model.addAttribute("plannerList", plannerList);

    }


}


