package com.team.semiTravelRecommend.controller.recommend;

import com.team.semiTravelRecommend.model.dto.recommend.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.recommend.TagDTO;
import com.team.semiTravelRecommend.paging.Pagenation;
import com.team.semiTravelRecommend.paging.SelectCriteria;
import com.team.semiTravelRecommend.service.RecommendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    // 추천 여행장소 리스트 맨처음에 보여짐
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
    // 태그선택시 호출
    @GetMapping("recommendDetail/{placeId}")
    public String travelDetail(Model model, @PathVariable(value = "placeId")int travelInfo){
        // 디테일 정보를 placeId로 찾는다.
        System.out.println("travelInfo = " + travelInfo);
        PlaceDTO travelDetail = recommendService.detailTravelInfo(travelInfo);
        int userNo = 1;
        int checkBookmark = recommendService.checkBookmark(userNo, travelInfo);
        System.out.println("checkBookmark = " + checkBookmark);
        if(checkBookmark == 1){
            model.addAttribute("checkBookmark", 1);
        }else{
            model.addAttribute("checkBookmark", 0);
        }
        System.out.println("travelDetail = " + travelDetail);
        // 찾은 정보를 모델에 저장하여 뷰에 전달
        model.addAttribute("travelInfo", travelDetail);

        return "recommend/recommendDetail";
    }

    @RequestMapping(value = "/checkingBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int checkingBookmark(int userNo,int placeId){
        int checkBookmark = recommendService.checkBookmark(userNo, placeId);

        if(checkBookmark == 1){
            return recommendService.deleteBookmark(userNo, placeId);
        }else {
            return recommendService.insertBookmark(userNo, placeId);

        }

    }
}
