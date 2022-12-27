package com.team.semiTravelRecomend.model.dto.record;

import lombok.Data;

import java.sql.Date;
 /**
    * Version : 1.0
   * 클래스명: RecordDTO
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 기록DTO
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Data
public class RecordDTO {

    private int record_no;
    private int city_code;
    private int user_no;
    private int file_no;
    private String rc_title;
    private String recode_detail;
    private Date travel_start_date;
    private Date travel_end_date;

}
