package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.record.*;
import com.team.semiTravelRecommend.paging.SelectCriteria;

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

    RecordDTO recordOne(int recordNo);

    int heartCheck(int recordNo, int userNo);

    List<RecordDTO> recordListPaging(SelectCriteria selectCriteria);

    int findAllCnt();

    List<LocationDTO> readLocation();

    List<TagDTO> readTag();

    List<CityDTO> readCity(String locCode);

    int saveFile(FileDTO imgFile) throws Exception;

    int returnFileNo(String changeName);

    int editRecord(RecordDTO record) throws Exception;

    int deleteImgFile(String changeName) throws Exception;

    int deleteRecord(int recordNo);
}
