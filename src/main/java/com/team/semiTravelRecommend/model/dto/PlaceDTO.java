package com.team.semiTravelRecommend.model.dto;

import lombok.Data;

/**
    * Version : 1.0
   * 클래스명: PlaceDTO
   * 작성일자 : 2023/01/01
 * 작성자 : heojaehong
   * 설명 : 장소 DTO
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
 @Data
public class PlaceDTO {

      private int rowNum;
     private int placeId;
     private String placeName;
     private String address;
     private String tel;
     private String plImgPath;
     private int contentType;
     private String tagCode;
     private String contentDetail;
     CityDTO cityDTO;

}
