package com.team.semiTravelRecomend.service;

import com.team.semiTravelRecomend.model.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    boolean registComment(CommentDTO comment);

    List<CommentDTO> showComment();
}
