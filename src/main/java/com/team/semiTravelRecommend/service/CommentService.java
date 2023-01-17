package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.CommentDTO;

import java.util.List;
 /**
    * Version : 1.0
   * 클래스명: CommentService
   * 작성일자 : 2023/01/16
 * 작성자 : heojaehong
   * 설명 : 댓글관련 서비스 인터페이스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
public interface CommentService {
    int registComment(CommentDTO comment);

    List<CommentDTO> showComment(int recordNo);

    int updateComment(CommentDTO comment);

    int deleteComment(int comNo);
}
