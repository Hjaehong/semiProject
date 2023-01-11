package com.team.semiTravelRecommend.controller.myPage;

import com.team.semiTravelRecommend.model.dto.record.CityDTO;
import com.team.semiTravelRecommend.model.dto.record.RecordDTO;
import com.team.semiTravelRecommend.model.dto.record.UserTagDTO;
import com.team.semiTravelRecommend.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {

        this.myPageService = myPageService;
    }

//    public UserTagDTO readUserInfo (int loginUserNo){}

    @GetMapping("myPage")
    public Model myPage (Model model){ // 마이페이지에 들어오면 이 메소드가 실행됨
        // 로그인 기능 연결 후 session에서 userNo 가져오기 (지금은 임의로 3으로 보냄)
        int loginUserNo = 3;


        List<CityDTO> myBadge = myPageService.readMyBadge(loginUserNo);
        UserTagDTO userInfo = myPageService.readUserInfo(loginUserNo);

        String tagName = userInfo.getTagDTO().getTagName();
        String[] tagList = tagName.split(",");

        // 확인용 출력
        System.out.println(userInfo);
        System.out.println(userInfo.getTagDTO().getTagCode());
        System.out.println(userInfo.getTagDTO().getTagName());

        System.out.println(myBadge.get(0));

        System.out.println(tagList[0]);
        System.out.println(tagList[1]);
        System.out.println(tagList[2]);

        model.addAttribute("UserInfo", userInfo);
        model.addAttribute("UserTag", tagList);
        model.addAttribute("UserBadge", myBadge);

        return model;
    }

    @GetMapping("myRecord")
    public Model myRecord(Model model){

        System.out.println("컨트롤러 매핑 동작 확인");

        int loginUserNo = 3;

        UserTagDTO userInfo = myPageService.readUserInfo(loginUserNo);

        model.addAttribute("UserInfo", userInfo);

        return model;
    }


}
