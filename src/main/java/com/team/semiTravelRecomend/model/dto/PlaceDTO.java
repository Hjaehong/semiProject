package com.team.semiTravelRecomend.model.dto;

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

     private String place_id;
     private String place_name;
     private String address;
     private String tel;
     private String pl_img_path;
     private int content_type;
     private String tag_code;
     private String content_detail;
     CityDTO cityDTO;

}
