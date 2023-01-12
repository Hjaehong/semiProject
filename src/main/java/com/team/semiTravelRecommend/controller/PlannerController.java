package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import com.team.semiTravelRecommend.service.PlannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/planner") //나중에 main으로 바꿔야할듯
public class PlannerController {

    private final PlannerService plannerService;


    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @GetMapping("/planner")
    public void insertPage() {}

    @PostMapping("/planner") // html에 action에 쓴 태그가 일치해야 함
    public ModelAndView insertPlanner(ModelAndView mv, PlannerDTO plannerDTO) throws Exception {

        System.out.println(plannerDTO.getStartDueDate());
        plannerService.insertPlanner(plannerDTO);
        mv.setViewName("redirect:/planner/list"); // 플래너 폴더에 플래너.html

        //rda.addFlashAttribute("successMessage", "등록에 성공");
        //경로를 메인으로
        //mv.setViewName("/");

        return mv;

    }

    @GetMapping("/list")
    public ModelAndView findAllPlanner(ModelAndView mv) {

        List<PlannerDTO> plannerList = plannerService.findAllPlanner();
        plannerList.stream().forEach((planner -> System.out.println("planer = " + planner)));

        mv.addObject("findAllPlanner", plannerList);
        mv.setViewName("planner/list");

        return mv;

    }

}
