package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.TagDTO;
import com.team.semiTravelRecommend.model.dto.UserResponse;
import com.team.semiTravelRecommend.service.MainPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: HomeController
   * 작성일자 : 2023/01/18
 * 작성자 : 손종범
   * 설명 :
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Slf4j
@Controller
public class HomeController {

    private final MainPageService mainPageService;

    public HomeController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/")
    public ModelAndView homeLogin(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserResponse loginMember,
                                  ModelAndView mv, HttpServletRequest request) {
        HttpSession session = request.getSession();


        if (loginMember == null) { // 로그인 정보가 없으면 0을
            mv.addObject("loginMember", 0);
            log.info("login fail={}", loginMember);
        }
        else { // 로그인 정보가 있으면 1을
            mv.addObject("loginMember", 1);
            log.info("login success={}", loginMember);
        }

        // 북마크 많이 된 순으로 출력 (가장 핫한 여행지 부분을 위한 코드)
        List<PlaceDTO> topLank = mainPageService.readTopLank();
        mv.addObject("TopLank", topLank);

        // 여행 키워드 출력 (키워드로 여행지 찾기 부분을 위한 코드)
        List<TagDTO> tagList = mainPageService.readTagList();

        List<TagDTO> tagGroup1 = new ArrayList<>();
        List<TagDTO> tagGroup2 = new ArrayList<>();
        List<TagDTO> tagGroup3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {tagGroup1.add(tagList.get(i));}
        for (int i = 10; i < 20; i++) {tagGroup2.add(tagList.get(i));}
        for (int i = 20; i < tagList.size(); i++) {tagGroup3.add(tagList.get(i));}

        mv.addObject("TagGroup1", tagGroup1);
        mv.addObject("TagGroup2", tagGroup2);
        mv.addObject("TagGroup3", tagGroup3);

        mv.setViewName("common/main");

        return mv;
    }

}
