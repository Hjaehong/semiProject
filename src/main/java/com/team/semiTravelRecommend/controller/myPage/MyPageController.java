package com.team.semiTravelRecommend.controller.myPage;

import com.team.semiTravelRecommend.model.dto.BookmarkDTO;
import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.CityDTO;
import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import com.team.semiTravelRecommend.model.dto.RecordDTO;
import com.team.semiTravelRecommend.model.dto.UserTagDTO;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: MyPageController
   * 작성일자 : 2023/01/17
 * 작성자 : heojaehong
   * 설명 : 마이페이지 컨트롤러
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {

        this.myPageService = myPageService;
    }

    // 마이페이지로 이동하는 메소드
    @GetMapping("myPage")
    public Model myPage (@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                         Model model){ // 마이페이지에 들어오면 이 메소드가 실행됨
        // 세션 유저 정보
        int loginUserNo = loginMember.getUserNo().intValue();

        Model userInfoModel = readUserInfo(model, loginUserNo);

        model.addAttribute("UserInfo", userInfoModel.getAttribute("UserInfo"));
        model.addAttribute("UserTag", userInfoModel.getAttribute("UserTag"));

        // 지금 이 페이지에서만 필요한 정보를 가져옴
        List<CityDTO> myBadge = myPageService.readMyBadge(loginUserNo);

        model.addAttribute("UserBadge", myBadge);
        model.addAttribute("loginMember", 1);

        return model;
    }
    // 마이페이지에서 내가 작성한 여행 기록 리스트 페이지로 이동하는 메소드
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
        model.addAttribute("loginMember", 1);

        return model;
    }

    @GetMapping("myHeart")
    public Model myHeart(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                             Model model){

        int loginUserNo = loginMember.getUserNo().intValue();
        Model userInfoModel = readUserInfo(model, loginUserNo);

        model.addAttribute("UserInfo", userInfoModel.getAttribute("UserInfo"));
        model.addAttribute("UserTag", userInfoModel.getAttribute("UserTag"));

        List<RecordDTO> myHeart = myPageService.readMyHeart(loginUserNo);

        model.addAttribute("MyHeart", myHeart);
        model.addAttribute("loginMember", 1);

        return model;
    }

    @GetMapping("myPlanner")
    public Model myPlanner(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                           Model model){

        int loginUserNo = loginMember.getUserNo().intValue(); // intValue 를 사용해서 int형으로 바꿔줌
        Model userInfoModel = readUserInfo(model, loginUserNo);

        model.addAttribute("UserInfo", userInfoModel.getAttribute("UserInfo")); // model.addAttribute() : model에 데이터를 담을 때 사용
        model.addAttribute("UserTag", userInfoModel.getAttribute("UserTag"));

        List<PlannerDTO> myPlanner = myPageService.readMyPlanner(loginUserNo);
        model.addAttribute("MyPlanner", myPlanner);
        model.addAttribute("loginMember", 1);

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
        model.addAttribute("loginMember", 1);

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
