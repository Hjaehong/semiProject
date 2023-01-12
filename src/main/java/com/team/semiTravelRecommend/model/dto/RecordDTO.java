package com.team.semiTravelRecommend.model.dto;

import com.team.semiTravelRecommend.model.dto.CityDTO;
import com.team.semiTravelRecommend.model.dto.TagDTO;
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

    private int rowNum;
    private int recordNo;
    private int userNo;
    private String rcTitle;
    private String rcDetail;
    private Date travelStartDate;
    private Date travelEndDate;
    private String recordTag;
    private String cityCode;
    private int imgFileNo;
    private UserDTO userDTO;
    private TagDTO tagDTO;
    private CityDTO cityDTO;
    private FileDTO fileDTO;

}
