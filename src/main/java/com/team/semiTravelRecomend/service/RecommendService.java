package com.team.semiTravelRecomend.service;

import com.team.semiTravelRecomend.model.dto.PlaceDTO;
import com.team.semiTravelRecomend.model.dto.TagDTO;

import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: RecommendService
   * 작성일자 : 2023/01/03
 * 작성자 : heojaehong
   * 설명 : 추천 서비스 인터페이스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
public interface RecommendService {

    List<PlaceDTO> showRecommend();
    List<PlaceDTO> tagRecommendTravel(String tag_code);
    List<TagDTO> showTag();
}
