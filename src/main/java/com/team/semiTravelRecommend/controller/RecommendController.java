package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.TagDTO;
import com.team.semiTravelRecommend.paging.Pagenation;
import com.team.semiTravelRecommend.paging.SelectCriteria;
import com.team.semiTravelRecommend.service.RecommendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
    * Version : 1.0
   * 클래스명: RecommendController
   * 작성일자 : 2023/01/02
 * 작성자 : heojaehong
   * 설명 : 추천여행지 컨트롤러
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
    // currPageNo 현재 페이지 번호, range 페이지 범위 -
    @GetMapping("travelRecommend")
    public Model recommendList(@RequestParam(value = "tag", required = false) String tagCode,  HttpServletRequest request,Model model){
        // paging.html에서 currentPage인 name을 가져온다.
        String currentPage = request.getParameter("currentPage");

        int pageNo = 1;
        if(currentPage != null && !"".equals(currentPage)) {
            // 파라미터로 전달 받은 값이 있을떄 그 값을 페이지 번호에
            pageNo =  Integer.parseInt(currentPage);
        }
        // 0 보다 작은 값이 넘어 올 경우 1페이지
        if(pageNo <= 0){
            pageNo = 1;
        }
        // 여행지 총 개수 카운트
        int totalCount = recommendService.findAllCnt();
        // 한페이지에 보여줄 게시물 수
        int limit = 8;
        // 한페이지에 보여줄 버튼 개수
        int buttonAmount = 5;
        // 페이징 처리를 위한 로직 호출
        SelectCriteria selectCriteria = null;
        // 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환
        selectCriteria = Pagenation.getSelectCriteria(pageNo,totalCount, limit, buttonAmount);
        System.out.println("selectCriteria = " + selectCriteria);

        // 조회
        List<PlaceDTO> travelList = recommendService.listPaging(selectCriteria);
        // 여행지 태그 조회
        List<TagDTO> tagList = recommendService.showTag();
        // 선택한 태그 여행지 조회
        List<PlaceDTO> tagchooselList = recommendService.tagRecommendTravel(tagCode);

        model.addAttribute("selectCriteria", selectCriteria);
        model.addAttribute("tagList", tagList);
        if( tagCode != null){
            model.addAttribute("travelList", tagchooselList);
        } else {
            model.addAttribute("travelList", travelList);
        }


        return model;
    }
//    @GetMapping("/paging")
//    public void page(HttpServletRequest request, Model model){
//
//    }
    // 취향에 맞춰 여행지를 보여주는 메소드
    @GetMapping("recommendDetail/{placeId}")
    public String travelDetail(Model model, @PathVariable(value = "placeId")String travelInfo){
        // 디테일 정보를 placeId로 찾는다.
        System.out.println("travelInfo = " + travelInfo);
        PlaceDTO travelDetail = recommendService.detailTravelInfo(travelInfo);

        System.out.println("travelDetail = " + travelDetail);
        // 찾은 정보를 모델에 저장하여 뷰에 전달
        model.addAttribute("travelInfo", travelDetail);

        return "recommend/recommendDetail";
    }


}
