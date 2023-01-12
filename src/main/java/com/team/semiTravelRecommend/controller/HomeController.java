package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.recommend.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.recommend.TagDTO;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.service.MainPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class HomeController {

    private final MainPageService mainPageService;

    public HomeController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

//    //    @GetMapping("/")
//    public String home() {
//        log.info("진입");
//        System.out.println("왔어요");
//        return "home";
//    }
//
//    //@GetMapping("/user/test")
//    @ResponseBody
//    public String test(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//
//        Object attribute = session.getAttribute(SessionConst.LOGIN_USER);
//
//        if (attribute == null) {
//            return "로그인 해주세요";
//        }
//        return "메인화면";
//    }

    @GetMapping("/")
    public ModelAndView homeLogin(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember, ModelAndView mv, HttpServletRequest request) {
        HttpSession session = request.getSession();

//        Object attribute = session.getAttribute(SessionConst.LOGIN_USER);
//
//        //세션에 회원 데이터가 없으면 홈
//        if (loginMember == null) {
//            return "home";
//        }
//        //세션이 유지되면 로그인으로 이동
//        model.addAttribute("loginMember", loginMember);
//        return "loginHome";

        if (loginMember == null) { // 로그인 정보가 없으면 0을
            mv.addObject("loginMember", 0);
        }
        else { // 로그인 정보가 있으면 1을
            mv.addObject("loginMember", 1);
        }

        List<PlaceDTO> topLank = mainPageService.readTopLank();
        List<TagDTO> tagList = mainPageService.readTagList();

        mv.addObject("TopLank", topLank);
        mv.addObject("TagList", tagList);

        mv.setViewName("common/main");

        return mv;
    }


}
