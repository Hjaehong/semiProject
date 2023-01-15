package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.CommentDTO;
import com.team.semiTravelRecommend.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: CommentMapper
   * 작성일자 : 2023/01/12
 * 작성자 : heojaehong
   * 설명 : 댓글 dao
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Mapper
public interface CommentMapper {
    // 댓글 등록
    int insertComment(CommentDTO commentDTO);
    // 댓글 목록
    List<CommentDTO> viewAllComment(int recordNo);
    // 댓글 수정
    int updateComment(CommentDTO comment);
    // comNo찾기
     String selectOne(CommentDTO comment);

     CommentDTO showNickname(int userNo);
 }
