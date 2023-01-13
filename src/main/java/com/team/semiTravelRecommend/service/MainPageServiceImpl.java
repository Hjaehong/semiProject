package com.team.semiTravelRecommend.service;

import com.team.semiTravelRecommend.model.dao.MainPageMapper;
import com.team.semiTravelRecommend.model.dto.recommend.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.recommend.TagDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MainPageServiceImpl implements MainPageService{

    private final MainPageMapper mainPageMapper;

    public MainPageServiceImpl(MainPageMapper mainPageMapper) {
        this.mainPageMapper = mainPageMapper;
    }


    @Override
    public List<PlaceDTO> readTopLank(){ return mainPageMapper.readTopLank(); }

    @Override
    public List<TagDTO> readTagList(){ return mainPageMapper.readTagList(); }

}
