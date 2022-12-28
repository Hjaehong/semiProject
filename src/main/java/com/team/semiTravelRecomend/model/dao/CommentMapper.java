package com.team.semiTravelRecomend.model.dao;

import com.team.semiTravelRecomend.model.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int insertComment(CommentDTO commentDTO);

    List<CommentDTO> viewAllComment();
}
