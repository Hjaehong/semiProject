package com.team.semiTravelRecommend.controller.myPage;

import com.team.semiTravelRecommend.model.dto.record.UserTagDTO;
import com.team.semiTravelRecommend.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {

        this.myPageService = myPageService;
    }

    @GetMapping("myPage")
    public Model myPage (Model model){
        // 마이페이지에 들어오면 이 메소드가 실행됨
        UserTagDTO userInfo = myPageService.readUserInfo();

        System.out.println(userInfo);
        System.out.println(userInfo.getTagDTO().getTagCode());
        System.out.println(userInfo.getTagDTO().getTagName());

        String tagName = userInfo.getTagDTO().getTagName();
        String[] tagList = tagName.split(",");
        System.out.println(tagList[0]);
        System.out.println(tagList[1]);
        System.out.println(tagList[2]);

        model.addAttribute("UserInfo", userInfo);
        model.addAttribute("UserTag", tagList);

        return model;
    }


}
