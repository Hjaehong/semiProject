package com.team.semiTravelRecommend.controller.myPage;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.recommend.BookmarkDTO;
import com.team.semiTravelRecommend.model.dto.CityDTO;
import com.team.semiTravelRecommend.model.dto.record.RecordDTO;
import com.team.semiTravelRecommend.model.dto.record.UserTagDTO;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {

        this.myPageService = myPageService;
    }

    @GetMapping("myPage")
    public Model myPage (@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                         Model model){ // 마이페이지에 들어오면 이 메소드가 실행됨

        // 로그인 기능 연결 후 session에서 userNo 가져오기 (지금은 임의로 3으로 보냄)
        int loginUserNo = loginMember.getUserNo().intValue();

        Model userInfoModel = readUserInfo(model, loginUserNo);

        model.addAttribute("UserInfo", userInfoModel.getAttribute("UserInfo"));
        model.addAttribute("UserTag", userInfoModel.getAttribute("UserTag"));

        // 지금 이 페이지에서만 필요한 정보를 가져옴
        List<CityDTO> myBadge = myPageService.readMyBadge(loginUserNo);

        model.addAttribute("UserBadge", myBadge);

        return model;
    }

    @GetMapping("myRecord")
    public Model myRecord(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                          Model model){

        // 로그인 기능 연결 후 session 에서 받아오기
        int loginUserNo = loginMember.getUserNo().intValue();
        Model userInfoModel = readUserInfo(model, loginUserNo);

        model.addAttribute("UserInfo", userInfoModel.getAttribute("UserInfo"));
        model.addAttribute("UserTag", userInfoModel.getAttribute("UserTag"));

        List<RecordDTO> myRecord = myPageService.readMyRecord(loginUserNo);

        model.addAttribute("MyRecord", myRecord);

        return model;
    }

    @GetMapping("myHeart")
    public Model readMyHeart(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                             Model model){

        int loginUserNo = loginMember.getUserNo().intValue();
        Model userInfoModel = readUserInfo(model, loginUserNo);

        model.addAttribute("UserInfo", userInfoModel.getAttribute("UserInfo"));
        model.addAttribute("UserTag", userInfoModel.getAttribute("UserTag"));

        List<RecordDTO> myHeart = myPageService.readMyHeart(loginUserNo);

        model.addAttribute("MyHeart", myHeart);

        return model;
    }
    @GetMapping("myBookmark")
    public Model readMyBookmark(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                                Model model){
        int loginUserNo = loginMember.getUserNo().intValue();
        Model userInfoModel = readUserInfo(model, loginUserNo);

        model.addAttribute("UserInfo", userInfoModel.getAttribute("UserInfo"));
        model.addAttribute("UserTag", userInfoModel.getAttribute("UserTag"));

        List<BookmarkDTO> myBookmark = myPageService.readMyBookmark(loginUserNo);

        model.addAttribute("myBookmark", myBookmark);

        return model;
    }

    /* 마이페이지에서 공통적으로 일어나는 로직을 따로 메소드로 만듦 */
    public Model readUserInfo(Model model, int loginUserNo){

        UserTagDTO userInfo = myPageService.readUserInfo(loginUserNo);

        String tagName = userInfo.getTagDTO().getTagName();
        String[] tagList = tagName.split(",");

        model.addAttribute("UserInfo", userInfo);
        model.addAttribute("UserTag", tagList);

        return model;
    }

}
