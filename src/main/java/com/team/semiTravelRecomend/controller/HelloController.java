package com.team.semiTravelRecomend.controller;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HelloController {

    @GetMapping("/travel-record")
    public String hello() {
        log.info(">>>>>>>>>>>> 진입 ");


        return "recode/travelRecord";

        //서버 사이드 렌더링 공부. JSP, thymeleaf, -- SSR 백엔드서버와 html 템플릿 엔진으로 화면 구성.


        //클라이언트 사이드 렌더링 -> 리액트, 앵귤러, 뷰 프론트엔드 기술 CSR -> 프론트엔드 백엔드 서버가 각각 띄어짐.
    }

    @GetMapping("itemForm")

    @PostMapping("/basic/items/add")
    public String addItem(@ModelAttribute Model model) {
        return "redirect:/";
    }




}
