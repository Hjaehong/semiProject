package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.MyPageMapper;
import com.team.semiTravelRecommend.model.dto.record.UserTagDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MyPageServiceImpl implements MyPageService{

    private final MyPageMapper myPageMapper;

    public MyPageServiceImpl(MyPageMapper myPageMapper) {

        this.myPageMapper = myPageMapper;
    }

    @Override
    public UserTagDTO readUserInfo(){
        return myPageMapper.readUserInfo();
    }

}
