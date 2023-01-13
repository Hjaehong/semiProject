package com.team.semiTravelRecommend.model.dao;

import com.team.semiTravelRecommend.model.dto.recommend.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.recommend.TagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainPageMapper {

    List<PlaceDTO> readTopLank();

    List<TagDTO> readTagList();
}
