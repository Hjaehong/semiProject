package com.team.semiTravelRecommend.controller.comment;

import com.team.semiTravelRecommend.model.dto.comment.CommentDTO;
import com.team.semiTravelRecommend.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping ("/comment")
    public void moveComment(){
        System.out.println("commentController 작동 중");
    }

    // 댓글 전체 확인 메소드
    @GetMapping("/result")
    public ModelAndView showComment(ModelAndView mv){
        List<CommentDTO> commentList = commentService.showComment();
        mv.addObject("commentList", commentList);
        mv.setViewName("/record/result");

        return mv;
    }
    @PostMapping("/comment")
     public ModelAndView registComment(ModelAndView mv, CommentDTO comment)throws Exception{
        commentService.registComment(comment);
        mv.setViewName("redirect:/record/result"); // /record/comment

        return mv;
    }

}
