package com.team.semiTravelRecomend.model.dto;

import lombok.Data;

/**
    * Version : 1.0
   * 클래스명: CityDTO
   * 작성일자 : 2023/01/01
 * 작성자 : heojaehong
   * 설명 : 도시DTO
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */

 @Data
public class CityDTO {

     private String city_code;
     private String loc_code;
     private String city_name;
     private String badge_img;
     private String local_code;
}
