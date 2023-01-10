package com.team.semiTravelRecommend.model.dto.comment;

import lombok.Data;
 /**
    * Version : 1.0
   * 클래스명: CommentDTO
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 댓글 DTO
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Data
public class CommentDTO {

    private int com_no;
    private int user_no;
    private int record_no;
    private String com_contain;
}
