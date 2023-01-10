package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.RecommendMapper;
import com.team.semiTravelRecommend.model.dto.recommend.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.recommend.TagDTO;
import com.team.semiTravelRecommend.paging.SelectCriteria;
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

    public List<PlaceDTO> tagRecommendTravel(String tagCode) { return recommendMapper.tagRecommendTravel(tagCode); }

    @Override
    public List<TagDTO> showTag() {
        return recommendMapper.showTag();
    }

    // 총 여행지 갯수 출력
    @Override
    public int findAllCnt() { return recommendMapper.countTravel(); }

    @Override
    public List<PlaceDTO> listPaging(SelectCriteria selectCriteria) {return recommendMapper.listPaging(selectCriteria);}

    @Override
    public PlaceDTO detailTravelInfo(String travelInfo) {
        return recommendMapper.travelDetail(travelInfo);
    }


}