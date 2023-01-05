package com.team.semiTravelRecomend.model.dao;

import com.team.semiTravelRecomend.model.dto.PlaceDTO;
import com.team.semiTravelRecomend.model.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;
 /**
    * Version : 1.0
   * 클래스명: RecommendMapper
   * 작성일자 : 2023/01/03
 * 작성자 : heojaehong
   * 설명 : db와 연결하는 dao 인터페이스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: RecommendMapper
   * 작성일자 : 2023/01/02
 * 작성자 : heojaehong
   * 설명 : db와 dto를 연결하는 dao(mapper)
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Mapper
public interface RecommendMapper {

    List<PlaceDTO> showRecommendTravel();
    List<PlaceDTO> tagRecommendTravel(String tag_code);
    List<TagDTO> showTag();
    // 총 개시물 수
    int countTravel();
    // 페이징 리스트
    List<PlaceDTO> listPaging();

 }
