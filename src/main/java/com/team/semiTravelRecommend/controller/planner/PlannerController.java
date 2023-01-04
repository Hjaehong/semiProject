package com.team.semiTravelRecommend.controller.planner;

import com.team.semiTravelRecommend.service.PlannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planner")
public class PlannerController {

    @GetMapping("/planner") // localhost:8090/planner 로 보여준다?
    public String plannerBoard() {
        return "planner"; // planner.html 로 접속한다?
    }

    @PostMapping("/planner/writepro") // html에 action에 쓴 태그가 일치해야 함
    public String plannerWritePro(/*매개변수에는 폼태그 안에 썼던 내용들 다 넣는듯*/ String placeContent, String dateContent) {

        System.out.println("여행지 : " + placeContent);
        System.out.println("여행일자 : " + dateContent);

        return "";
    
    }
//db에 저장하기 위해선 레퍼지토리가 필요함
    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService) { this.plannerService = plannerService; }
}
