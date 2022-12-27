package com.team.semiTravelRecomend.controller;

import com.team.semiTravelRecomend.model.dto.CommentDTO;
import com.team.semiTravelRecomend.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/record")
public class CommentController {

    private CommentService commentService;

    @GetMapping ("/comment")
    public void moveComment(){
        System.out.println("commentController 작동 중");
    }

    @PostMapping("/comment")
     public ModelAndView registComment(ModelAndView mv, CommentDTO comment)throws Exception{
        commentService.registComment(comment);
        mv.setViewName("/comment"); // /record/comment

        return mv;
    }

}
