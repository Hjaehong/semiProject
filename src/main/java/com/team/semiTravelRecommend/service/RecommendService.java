package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.TagDTO;
import com.team.semiTravelRecommend.paging.SelectCriteria;

import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: RecommendService
   * 작성일자 : 2023/01/03
 * 작성자 : heojaehong
   * 설명 : 추천 서비스 인터페이스
   * 수정일자 : 2023/01/04
   * 수정자 :
   * 수정내역 : 페이징 기능 추가
 */
public interface RecommendService {
    // 여행지 추천 리스트 보여주기
    List<PlaceDTO> showRecommend();
    // 여행지 태그에 맞는 여행지 추천
    List<PlaceDTO> tagRecommendTravel(String tagCode);
    // 여행지 태그 보여주기
    List<TagDTO> showTag();
    // 여행지 전체 갯수 카운트
    int findAllCnt(String tagCode);
    // 페이징 리스트
    List<PlaceDTO> listPaging(SelectCriteria selectCriteria);

    // 여행지의 자세한 정보
     PlaceDTO detailTravelInfo(int travelInfo);
    // 북마크 관련 서비스
     // 북마크 확인
     int checkBookmark(int userNo, int travelInfo);
    // 북마크 삭제
     int deleteBookmark(int userNo, int placeId);
    // 북마크 생성
     int insertBookmark(int userNo, int placeId);
 }
