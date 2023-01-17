package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.BookmarkDTO;
import com.team.semiTravelRecommend.model.dao.MyPageMapper;
import com.team.semiTravelRecommend.model.dto.CityDTO;
import com.team.semiTravelRecommend.model.dto.PlannerDTO;
import com.team.semiTravelRecommend.model.dto.RecordDTO;
import com.team.semiTravelRecommend.model.dto.UserTagDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MyPageServiceImpl implements MyPageService{

    private final MyPageMapper myPageMapper;

    public MyPageServiceImpl(MyPageMapper myPageMapper) {

        this.myPageMapper = myPageMapper;
    }

    @Override
    public UserTagDTO readUserInfo(int loginUserNo){
        return myPageMapper.readUserInfo(loginUserNo);
    }

    @Override
    public List<CityDTO> readMyBadge(int loginUserNo){
        return myPageMapper.readMyBadge(loginUserNo);
    }

    @Override
    public List<RecordDTO> readMyRecord(int loginUserNo) { return myPageMapper.readMyRecord(loginUserNo); }

    @Override
    public List<RecordDTO> readMyHeart(int loginUserNo) { return myPageMapper.readMyHeart(loginUserNo); }

    @Override
    public List<PlannerDTO> readMyPlanner(int loginUserNo) { return myPageMapper.readMyPlanner(loginUserNo); }

    @Override
    public List<BookmarkDTO> readMyBookmark(int loginUserNo) {
        return myPageMapper.readMyBookMark(loginUserNo);
    }

}
