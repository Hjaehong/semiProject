package com.team.semiTravelRecomend.service;

import com.team.semiTravelRecomend.model.dto.record.RecordDTO;
import org.springframework.stereotype.Service;
 /**
    * Version : 1.0
   * 클래스명: RecordService
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 기록 서비스 인터페이스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
public interface RecordService {

     int insertRecord(RecordDTO recordDTO);
}
