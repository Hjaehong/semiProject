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
    public PlaceDTO detailTravelInfo(int travelInfo) {
        return recommendMapper.travelDetail(travelInfo);
    }

    @Override
    public int checkBookmark(int userNo, int travelInfo) {
        return recommendMapper.checkBookmark(userNo, travelInfo);
    }

    @Override
    public int deleteBookmark(int userNo, int placeId) {
        int result = recommendMapper.deleteBookmark(userNo, placeId);

        if(result <= 0){
            System.out.println("북마크 삭제 오류 발생 ");
        }
        return result > 0 ? 1 : 0;
    }

    @Override
    public int insertBookmark(int userNo, int placeId) {
        int result = recommendMapper.insertBookmark(userNo, placeId);

        if(result <= 0){
            System.out.println("북마크 삭제 오류 발생 ");
        }
        return result > 0 ? 2 : 0;
    }


}
