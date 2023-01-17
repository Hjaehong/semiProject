package com.team.semiTravelRecommend.controller.planner;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.record.PlannerDTO;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.service.PlannerService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("planner")
public class PlannerController {

    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @GetMapping("plannerWrite")
    public void planner (){
        // session 정보를 확인해서 userNo을 가져와서 프론트단에 뿌려주고
        // hidden 처리해서 Post 할때 같이 보내줘야 할 듯?!
    }

    @PostMapping("plannerWrite")
    public ModelAndView writePlanner (@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                                      ModelAndView mv, PlannerDTO planner, RedirectAttributes rttr){

        System.out.println(planner.getEndDueDate());
        System.out.println( "숙소정보확인" + planner.getLodgingInfo());

        int userNo = loginMember.getUserNo().intValue();
        planner.setUserNo(userNo);

        plannerService.insertPlanner(planner);

        mv.setViewName("redirect:/myPage/myPlanner");
        // 작성하면 바로 마이페이지의 myPlan으로 넘어가게 설정
        rttr.addFlashAttribute("successMessage", "작성 완료!");

        return mv;
    }

    @GetMapping("plannerDetail/{planNo}")
    public ModelAndView plannerOne (ModelAndView mv, @PathVariable("planNo") int planNo){

        PlannerDTO planner = plannerService.plannerOne(planNo);

        mv.addObject("Planner", planner);
        mv.setViewName("planner/plannerDetail");

        return mv;
    }

    @PostMapping("editPlanner")
    public ModelAndView readPlanner (ModelAndView mv, int planNo){
        // planNo을 받아서 수정창에 기존의 정보를 띄워주는 메소드

        PlannerDTO planner = plannerService.plannerOne(planNo);

        mv.addObject("Planner", planner);
        mv.setViewName("planner/plannerEdit");

        return mv;
    }

    @PostMapping("plannerEdit")
    public ModelAndView editPlanner (ModelAndView mv, PlannerDTO planner, RedirectAttributes rttr){

        System.out.println("수정 메소드 호출 확인용");
        plannerService.editPlanner(planner);

        mv.setViewName("redirect:/myPage/myPlanner");
        rttr.addFlashAttribute("successMessage", "수정 완료!");

        return mv;
    }

    @PostMapping("deletePlanner")
    public ModelAndView deletePlanner (ModelAndView mv, int planNo, RedirectAttributes rttr){

        plannerService.deletePlanner(planNo);

        mv.setViewName("redirect:/myPage/myPlanner");
        rttr.addFlashAttribute("successMessage", "삭제 완료!");

        return mv;
    }
}
