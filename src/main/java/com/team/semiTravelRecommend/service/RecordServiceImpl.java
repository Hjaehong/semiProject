package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.RecordMapper;
import com.team.semiTravelRecommend.model.dto.record.CityDTO;
import com.team.semiTravelRecommend.model.dto.record.LocationDTO;
import com.team.semiTravelRecommend.model.dto.record.RecordDTO;
import com.team.semiTravelRecommend.model.dto.record.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
    * Version : 1.0
   * 클래스명: RecordServiceImpl
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 서비스 인터페이스 구현 클래스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Service
public class RecordServiceImpl implements RecordService{

    private final RecordMapper recordMapper;

     public RecordServiceImpl(RecordMapper recordMapper) {
         this.recordMapper = recordMapper;
     }

     @Override
     public int insertRecord(RecordDTO recordDTO) {
         return 0;
     }

     @Override
     public RecordDTO readRecordOne(int recordNo){
         return recordMapper.readRecordOne(recordNo);
     }

     @Override
     public List<RecordDTO> recordList(){ return recordMapper.recordList(); }

    @Override
    public List<LocationDTO> readLocation(){ return recordMapper.readLocation(); }

    @Override
    public List<TagDTO> readTag(){ return recordMapper.readTag(); }

    @Override
    public List<CityDTO> readCity(String locCode) { return recordMapper.readCity(locCode);}
 }
