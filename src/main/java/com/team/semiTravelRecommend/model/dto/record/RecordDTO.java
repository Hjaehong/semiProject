package com.team.semiTravelRecommend.model.dto.record;

import lombok.Data;

import java.sql.Date;
 /**
    * Version : 1.0
   * 클래스명: RecordDTO
   * 작성일자 : 2022/12/27
   * 작성자 : heojaehong
   * 설명 : 여행기록DTO
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Data
public class RecordDTO {

    private int recordNo;
    private int fileNo;
    private String rcTitle;
    private String rcDetail;
    private Date travelStartDate;
    private Date travelEndDate;
    private String cityCode;
    private CityDTO cityDTO;
    private UserDTO userDTO;

}
