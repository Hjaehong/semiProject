package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.TagDTO;

import java.util.List;

public interface MainPageService {

    List<PlaceDTO> readTopLank();

    List<TagDTO> readTagList();
}
