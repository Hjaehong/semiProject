package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.comment.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: CommentMapper
   * 작성일자 : 2023/01/16
 * 작성자 : heojaehong
   * 설명 : 댓글 매퍼 클래스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Mapper
public interface CommentMapper {
    // 댓글입력
    int insertComment(CommentDTO commentDTO);
    // 댓글리스트 조회
    List<CommentDTO> viewAllComment(int recordNo);
    // 댓글 수정
    int updateComment(CommentDTO comment);
    // 댓글 삭제
     int deleteComment(int comNo);
 }
