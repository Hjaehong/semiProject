package com.team.semiTravelRecomend.controller;

import com.team.semiTravelRecomend.model.dto.PlaceDTO;
import com.team.semiTravelRecomend.model.dto.TagDTO;
import com.team.semiTravelRecomend.service.RecommendService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
    * Version : 1.0
   * 클래스명: RecommendController
   * 작성일자 : 2023/01/02
 * 작성자 : heojaehong
   * 설명 : 컨트롤러
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    // 생성자 주입
    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }


    // 추천 여행장소 리스트 보여주는 메소드
    @GetMapping("/travelRecommend")
    public Model recommendList(Model model){
        List<PlaceDTO> travelList = recommendService.showRecommend();
        List<TagDTO> tagList = recommendService.showTag();
        model.addAttribute("travelList", travelList);
        model.addAttribute("tagList", tagList);

        return model;
    }
    // 취향에 맞춰 여행지를 보여주는 메소드
    @PostMapping("/travelRecommend")
    public void checkTag(@RequestParam(value = "tag", required = false) String tag_code,Model model){

        List<PlaceDTO> travelList = recommendService.tagRecommendTravel(tag_code);
        List<TagDTO> tagList = recommendService.showTag();

        model.addAttribute("travelList", travelList);
        model.addAttribute("tagList", tagList);
        System.out.println("tag_code = " + tag_code);
    }

    // 여행지 클릭시 디테일 보여주는 메소드
    @GetMapping("/placeDetail")
    public void detail(){

    }
}
