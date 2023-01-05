package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.record.*;

import java.util.List;

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

    RecordDTO readRecordOne(int recordNo);

    List<RecordDTO> recordList();

    List<LocationDTO> readLocation();

    List<TagDTO> readTag();

    List<CityDTO> readCity(String locCode);

    int saveFile(FileDTO imgFile) throws Exception;
}
