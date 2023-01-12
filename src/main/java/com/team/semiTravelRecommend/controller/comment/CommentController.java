package com.team.semiTravelRecommend.controller.comment;

import com.team.semiTravelRecommend.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
    * Version : 1.0
   * 클래스명: CommentController
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 댓글 컨트롤러
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



}
