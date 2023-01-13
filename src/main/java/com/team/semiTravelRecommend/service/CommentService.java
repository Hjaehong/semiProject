package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.comment.CommentDTO;

import java.util.List;

public interface CommentService {
    int registComment(CommentDTO comment);

    List<CommentDTO> showComment(int recordNo);
}
