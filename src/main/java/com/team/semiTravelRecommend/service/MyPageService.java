package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.recommend.BookmarkDTO;
import com.team.semiTravelRecommend.model.dto.CityDTO;
import com.team.semiTravelRecommend.model.dto.record.RecordDTO;
import com.team.semiTravelRecommend.model.dto.record.UserTagDTO;

import java.util.List;

public interface MyPageService {

    UserTagDTO readUserInfo(int loginUserNo);

    List<CityDTO> readMyBadge(int loginUserNo);

    List<RecordDTO> readMyRecord(int loginUserNo);

    List<RecordDTO> readMyHeart(int loginUserNo);

    List<BookmarkDTO> readMyBookmark(int loginUserNo);
}
