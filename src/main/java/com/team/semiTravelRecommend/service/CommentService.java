package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.CommentDTO;

import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: CommentService
   * 작성일자 : 2023/01/12
 * 작성자 : heojaehong
   * 설명 : 댓글 서비스 클래스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
public interface CommentService {
    // 댓글 등록
    int registComment(CommentDTO comment);
    // 댓글 목록 출력
    List<CommentDTO> showComment(int recordNo);
    // 댓글 수정
    int updateComment(CommentDTO comment);
    // comNo 찾기
     String selectOne(CommentDTO comment);
 }
