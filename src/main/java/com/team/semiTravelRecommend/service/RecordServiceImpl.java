package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.RecordMapper;
import com.team.semiTravelRecommend.model.dto.FileDTO;
import com.team.semiTravelRecommend.model.dto.RecordDTO;
import com.team.semiTravelRecommend.model.dto.*;
import com.team.semiTravelRecommend.paging.SelectCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService{

    private final RecordMapper recordMapper;

     public RecordServiceImpl(RecordMapper recordMapper) {
         this.recordMapper = recordMapper;
     }

     @Override
     public int insertRecord(RecordDTO recordDTO) {
         int result = recordMapper.insertRecord(recordDTO);

         if(result <= 0) {
             System.out.println("여행기록작성실패");
         }
         return result > 0 ? 1 : 0;
     }

     @Override
     public RecordDTO recordOne(int recordNo){
         return recordMapper.recordOne(recordNo);
     }

    @Override
    public int heartCheck(int recordNo, int userNo){

         return recordMapper.heartCheck(recordNo, userNo);
     }

    @Override
    public int deleteHeart(int recordNo, int userNo){
        int result = recordMapper.deleteHeart(recordNo, userNo);

        if(result <= 0) {
            System.out.println("좋아요취소실패");
        }
        return result > 0 ? 1 : 0;
    }

    @Override
    public int insertHeart(int recordNo, int userNo){
        int result = recordMapper.insertHeart(recordNo, userNo);

        if(result <= 0) {
            System.out.println("좋아요실패");
        }
        return result > 0 ? 2 : 3;
    }

     @Override
     public List<RecordDTO> recordListPaging(SelectCriteria selectCriteria){
         List<RecordDTO> recordList = recordMapper.recordListPaging(selectCriteria);
         System.out.println("recordList입니다 = " + recordList);
         return recordList;}

    @Override
    public int findAllCnt(){ return recordMapper.findAllCnt(); }

    @Override
    public List<LocationDTO> readLocation(){ return recordMapper.readLocation(); }

    @Override
    public List<TagDTO> readTag(){ return recordMapper.readTag(); }

    @Override
    public List<CityDTO> readCity(String locCode) { return recordMapper.readCity(locCode); }

    @Override
    public int saveFile(FileDTO imgFile) throws Exception {
         int result = recordMapper.saveFile(imgFile);

         if(result <= 0) {
             throw new Exception("이미지 저장 실패");
         }
         return result > 0 ? 1 : 0;
     }

     @Override
    public int returnFileNo(String changeName) { return recordMapper.returnFileNo(changeName); }

    @Override
    public int editRecord(RecordDTO record) throws Exception {
        int result = recordMapper.editRecord(record);

        if(result <= 0) {
            throw new Exception("수정 실패");
        }
        return result > 0 ? 1 : 0;
    }

    @Override
    public int deleteImgFile(String changeName) throws Exception {
         int result = recordMapper.deleteImgFile(changeName);

        if(result <= 0) {
            throw new Exception("수정 실패");
        }
        return result > 0 ? 1 : 0;
    }
    @Override
    public int deleteRecord(int recordNo) { return recordMapper.deleteRecord(recordNo); }

    // 임시
    @Override
    public List<RecordDTO> recordList() {
        System.out.println("서비스 구현체 통과");
        return recordMapper.recordList();
    }


}
