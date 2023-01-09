package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import com.team.semiTravelRecommend.service.MyPlannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPlannerController {

    private final MyPlannerService myPlannerService;

    public MyPlannerController(MyPlannerService myPlannerService) {
        this.myPlannerService = myPlannerService;
    }

    /*@GetMapping("myPlanner")
    public void readPage() {
        System.out.println("haha");     //확인용
    }*/

    @GetMapping("/myPlanner")
    public ModelAndView findPlannerTitle(@RequestParam Integer userCode, ModelAndView mv) {
        System.out.println(userCode);
        List<PlannerDTO> plannerList = myPlannerService.findPlannerTitle(userCode);
        plannerList.stream().forEach((planner -> System.out.println("planer = " + planner)));

        mv.addObject("plannerList", plannerList);
        mv.setViewName("mypage/myPlanner");

        return mv;

    }

/*    @GetMapping("/myPlanner")
    public ModelAndView readPage(@PathVariable("myPlanner") int userCode) {

        System.out.println("hahaha");
        ModelAndView mv = new ModelAndView();

        List<PlannerDTO> plannerList = myPlannerService.findPlannerTitle(userCode);
        plannerList.forEach((planner -> System.out.println("planer = " + planner)));

        mv.addObject("plannerList", plannerList);
        mv.setViewName("mypage/myPlanner/");

        return mv;


    }*/

    /*public ModelAndView findPlannerTitle(ModelAndView mv, int userCode) {

        List<PlannerDTO> plannerList = myPlannerService.findPlannerTitle(userCode);
        plannerList.stream().forEach((planner -> System.out.println("planer = " + planner)));

        mv.addObject("plannerList", plannerList);
        mv.setViewName("myPlanner");

        return mv;

    }*/

  /*  @GetMapping("/myPlanner/{code}")
    public ModelAndView insertPage(@PathVariable Integer code) {

        System.out.println("hoho");
        System.out.println("code : " + code);
        ModelAndView mv = new ModelAndView();

        List<PlannerDTO> plannerList = myPlannerService.findPlannerByCode(code);
        plannerList.forEach((planner -> System.out.println("planer = " + planner)));

        mv.addObject("plannerList", plannerList);
        mv.setViewName("mypage/myPlanner/");



        return mv;
    }*/

/*    @GetMapping("/myPlanner/detail")
    public ModelAndView findPlannerList(int code, ModelAndView mv) {


        List<PlannerDTO> plannerList = myPlannerService.findPlannerByCode(code);
        plannerList.stream().forEach((planner -> System.out.println("planer = " + planner)));

        mv.addObject("plannerList", plannerList);
        mv.setViewName("myPlanner/detail");

        return mv;

    }*/



    /*@PostMapping("/myPlanner")
    public ModelAndView findPlannerList(ModelAndView mv) {

        List<PlannerDTO> plannerList = myPlannerService.findPlannerByCode();
        plannerList.forEach((planner -> System.out.println("planer = " + planner)));

        mv.addObject("plannerList", plannerList);
        mv.setViewName("planner/myPlanner");

        return mv;*/

    }

    /*@GetMapping
    public ModelAndView findPlanner(ModelAndView mv) {

        String planner = myPlannerService.findPlannerByCode();

        mv.addObject("planner", planner);
        mv.setViewName("planner/myPlanner");

        return mv;

    }*/


