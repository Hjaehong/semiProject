package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import com.team.semiTravelRecommend.service.MyPlannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPlannerController {

    private final MyPlannerService myPlannerService;

    public MyPlannerController(MyPlannerService myPlannerService) {
        this.myPlannerService = myPlannerService;
    }

    @GetMapping("/myPlanner")
    public ModelAndView findPlannerTitle(ModelAndView mv, String userNo) {

        List<PlannerDTO> plannerList = myPlannerService.findPlannerTitle(Integer.parseInt(userNo));
        mv.addObject("plannerList", plannerList);
        mv.setViewName("mypage/myPlanner");

        return mv;
    }

    @GetMapping("/myPlannerDetail")
    public ModelAndView findPlannerNo(ModelAndView mv, String planNo) {

        PlannerDTO planner = myPlannerService.findPlannerNo(Integer.parseInt(planNo));
        mv.addObject("planner", planner);
        mv.setViewName("mypage/myPlannerDetail");

        return mv;
    }

    @GetMapping("/myPlanner/delete")
    public ModelAndView deletePlanner(ModelAndView mv, int planNo, RedirectAttributes rttr) {

//        RedirectAttributes rttr 메세지처리할때 사용함
        int result = myPlannerService.deletePlanner(planNo);
        mv.setViewName("redirect:/mypage/myPlanner?userNo=9999"); // 딜리트떄에는 리다이렉트를 사용한다
                                            //"?userNo=" + userNo   나중에 수정해야함
        return mv;
    }

    /*수정할 화면을 보여준다(승재가 알려준거! 이건 됨)*/
/*    @GetMapping("/myPlanner/update")
    public ModelAndView showUpdatePlanner(ModelAndView mv, int planNo) {


        mv.addObject("planner", myPlannerService.findPlannerNo(planNo)); //findPlannerNo를 왜 썼는지 알아야함
        mv.setViewName("mypage/myPlannerUpdate");

        return mv;
    }*/

    /*위에껄 내가 좀 고침*/
    @GetMapping("/myPlanner/showUpdate")
    public ModelAndView showUpdatePlanner(ModelAndView mv, int planNo) {

        mv.addObject("planner", myPlannerService.findPlannerNo(planNo)); //findPlannerNo를 왜 썼는지 알아야함
        mv.setViewName("mypage/myPlannerUpdate");

        return mv;
    }

    /*화면에서 수정한다(딜리트랑 업데이트랑 같다?)*/
/*    @PostMapping("/mypage/myPlanner/update")
    public ModelAndView updatePlanner(String planNo, ModelAndView mv, RedirectAttributes rttr){
        System.out.println("///////////////////////");
        mv.addObject("planner", myPlannerService.updatePlanner(Integer.parseInt(planNo))) ; //지금 얘가 안먹는다 씨발!!!!!!!!!
        System.out.println("12312312312312");
        mv.setViewName("redirect:/mypage/myPlannerDetail?planNo=${planner.planNo}");

        return mv;
    }*/



/*    @PostMapping("/myPlanner/update")
    public ModelAndView updatePlanner(PlannerDTO planner, ModelAndView mv) {

        myPlannerService.updatePlanner(planner);
        mv.setViewName("redirect:/mypage/myPlannerDetail");

        return mv;
    }*/




/*    @PostMapping("/mypage/myPlanner/update")
    public ModelAndView updatePlanner(ModelAndView mv, PlannerDTO plannerDTO) {
        System.out.println("1111");
        myPlannerService.updatePlanner(plannerDTO);
        mv.addObject("planNo", plannerDTO.getPlanNo()).setViewName("redirect:/mypage/myPlannerDetail");

        return mv;
    }*/

    @PostMapping("/update")
    public ModelAndView updatePlanner(ModelAndView mv, PlannerDTO planner) {
        System.out.println(planner);
        System.out.println("컨트롤러 동작확인");
        myPlannerService.updatePlanner(planner.getPlanNo());
        mv.addObject("planNo", planner.getPlanNo());
        mv.setViewName("redirect:/mypage/myPlannerDetail/{planNo}");
        return mv;
    }
}


