package com.team.semiTravelRecomend.service;

import com.team.semiTravelRecomend.model.dao.RecommendMapper;
import com.team.semiTravelRecomend.model.dto.PlaceDTO;
import com.team.semiTravelRecomend.model.dto.TagDTO;
import com.team.semiTravelRecomend.paging.SelectCriteria;
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

    // 총 여행지 갯수 출력
    @Override
    public int findAllCnt() { return recommendMapper.countTravel(); }

    @Override
    public List<PlaceDTO> listPaging(SelectCriteria selectCriteria) {

        List<PlaceDTO> pagingList = recommendMapper.listPaging(selectCriteria);
        return pagingList;
    }


}
