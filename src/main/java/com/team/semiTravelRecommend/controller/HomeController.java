package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

//    @GetMapping("/")
    public String home() {
        log.info("진입");
        System.out.println("왔어요");
        return "home";
    }

    //@GetMapping("/user/test")
    @ResponseBody
    public String test(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Object attribute = session.getAttribute(SessionConst.LOGIN_USER);

        if (attribute == null) {
            return "로그인 해주세요";
        }
        return "메인화면";
    }

    @GetMapping("/")
    public String homeLogin(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserResponse attribute = (UserResponse) session.getAttribute(SessionConst.LOGIN_USER);

        //세션에 회원 데이터가 없으면 홈
        if (attribute == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("loginMember", attribute.getUserId());
        return "loginHome";
    }

}


