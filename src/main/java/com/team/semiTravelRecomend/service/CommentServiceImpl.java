package com.team.semiTravelRecomend.service;

import com.team.semiTravelRecomend.model.dao.CommentMapper;
import com.team.semiTravelRecomend.model.dto.CommentDTO;
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
    public boolean registComment(CommentDTO comment) {
        int result = commentMapper.insertComment(comment);

        if( result < 1) System.out.println("댓글 등록 실패!");
        return result > 0 ? true : false;
    }
    // 댓글 조회
    @Override
    public List<CommentDTO> showComment() {
        return commentMapper.viewAllComment();
    }

}
