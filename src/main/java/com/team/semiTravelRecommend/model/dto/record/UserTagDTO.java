package com.team.semiTravelRecommend.model.dto.record;

import com.team.semiTravelRecommend.model.recommend.TagDTO;
import lombok.Data;

@Data
public class UserTagDTO {

    private UserDTO userDTO;
    private TagDTO tagDTO;
}
