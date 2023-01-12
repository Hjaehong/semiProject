package com.team.semiTravelRecommend.model.dto;

import com.team.semiTravelRecommend.model.dto.TagDTO;
import lombok.Data;

@Data
public class UserTagDTO {

    private UserDTO userDTO;
    private TagDTO tagDTO;
}
