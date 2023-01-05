package com.team.semiTravelRecomend.controller;

import com.team.semiTravelRecomend.model.dto.User;
import com.team.semiTravelRecomend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HelloController {

    private final UserService userService;

    @GetMapping("/travel-record")
    public String hello() {
        log.info(">>>>>>>>>>>> 진입 ");

        return "recode/travelRecord";
    }

    @GetMapping("/user-signup")
    public String signUpForm() {
        return "user/signup";
    }

    //여기로 타지지를 않음
    @PostMapping("/user-signup")
    public String signUp(User user) {
        userService.save(user);
        log.info(">>>>>>>>>>>> 진입 ");
        return "user/login"; //로그인 구현 예정
    }


//    @GetMapping("itemForm")
//
//    @PostMapping("/basic/items/add")
//    public String addItem(@ModelAttribute Model model) {
//        return "redirect:/";
//    }







}
