package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.comment.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int insertComment(CommentDTO commentDTO);

    List<CommentDTO> viewAllComment();
}
