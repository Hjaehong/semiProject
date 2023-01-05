package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.record.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
    * Version : 1.0
   * 클래스명: RecordMapper
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : db와 연결하는 dao클래스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Mapper
public interface RecordMapper {

    RecordDTO readRecordOne(int recordNo);
    List<RecordDTO> recordList();

    List<LocationDTO> readLocation();

    List<TagDTO> readTag();

    List<CityDTO> readCity(String locCode);
    int saveFile(FileDTO imgFile);
 }
