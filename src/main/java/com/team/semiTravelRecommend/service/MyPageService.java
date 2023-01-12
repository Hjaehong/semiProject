package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dto.record.CityDTO;
import com.team.semiTravelRecommend.model.dto.record.RecordDTO;
import com.team.semiTravelRecommend.model.dto.record.UserTagDTO;
import net.bytebuddy.dynamic.scaffold.TypeWriter;

import java.util.List;

public interface MyPageService {

    UserTagDTO readUserInfo(int loginUserNo);

    List<CityDTO> readMyBadge(int loginUserNo);

    List<RecordDTO> readMyRecord(int loginUserNo);

    List<RecordDTO> readMyHeart(int loginUserNo);
}
