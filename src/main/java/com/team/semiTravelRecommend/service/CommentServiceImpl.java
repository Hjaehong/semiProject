package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.CommentMapper;
import com.team.semiTravelRecommend.model.dto.CommentDTO;
import com.team.semiTravelRecommend.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{


    private final CommentMapper commentMapper;
    // 생성자 주입
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    // 댓글 등록 메소드
    @Override
    public int registComment(CommentDTO comment) {
        int result = commentMapper.insertComment(comment);

        if( result < 1) System.out.println("댓글 등록 실패!");
        return result;
    }
    // 댓글 조회
    @Override
    public List<CommentDTO> showComment(int recordNo) {
        return commentMapper.viewAllComment(recordNo);
    }

    @Override
    public int updateComment(CommentDTO comment) {
        int result = commentMapper.updateComment(comment);

        if( result < 1) System.out.println("댓글 수정 실패!");
        return result;
    }

    @Override
    public int deleteComment(int comNo) {
        int result = commentMapper.deleteComment(comNo);

        if( result < 1) System.out.println("댓글 삭제 실패!");
        return result;
    }


}
