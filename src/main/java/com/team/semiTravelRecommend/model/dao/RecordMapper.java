package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.FileDTO;
import com.team.semiTravelRecommend.model.dto.RecordDTO;
import com.team.semiTravelRecommend.model.dto.*;
import com.team.semiTravelRecommend.paging.SelectCriteria;
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

    RecordDTO recordOne(int recordNo);

    int heartCheck(int recordNo, int userNo);

    int deleteHeart(int recordNo, int userNo);

    int insertHeart(int recordNo, int userNo);

    List<RecordDTO> recordListPaging(SelectCriteria selectCriteria);

    int findAllCnt();

    List<LocationDTO> readLocation();

    List<TagDTO> readTag();

    List<CityDTO> readCity(String locCode);
    int saveFile(FileDTO imgFile);

    int insertRecord(RecordDTO recordDTO);

    int returnFileNo(String changeName);

    int editRecord(RecordDTO recorddTO);

    int deleteImgFile(String changeName);

    int deleteRecord(int recordNo);


    List<RecordDTO> recordList();
}
