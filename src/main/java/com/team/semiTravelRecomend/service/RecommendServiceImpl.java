package com.team.semiTravelRecomend.service;

import com.team.semiTravelRecomend.model.dao.RecommendMapper;
import com.team.semiTravelRecomend.model.dto.PlaceDTO;
import com.team.semiTravelRecomend.model.dto.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecommendServiceImpl implements RecommendService{

    private final RecommendMapper recommendMapper;

    public RecommendServiceImpl(RecommendMapper recommendMapper) {
        this.recommendMapper = recommendMapper;
    }

    @Override
    public List<PlaceDTO> showRecommend() {
        return recommendMapper.showRecommendTravel();
    }

    public List<PlaceDTO> tagRecommendTravel(String tag_code) {
        return recommendMapper.tagRecommendTravel(tag_code);
    }

    @Override
    public List<TagDTO> showTag() {
        return recommendMapper.showTag();
    }
}
