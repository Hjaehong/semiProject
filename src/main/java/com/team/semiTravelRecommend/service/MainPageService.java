package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.recommend.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.recommend.TagDTO;

import java.util.List;

public interface MainPageService {

    List<PlaceDTO> readTopLank();

    List<TagDTO> readTagList();
}
