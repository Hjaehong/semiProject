package com.team.semiTravelRecommend.model.recommend;

import com.team.semiTravelRecommend.model.dto.record.UserDTO;
import lombok.Data;

@Data
public class BookmarkDTO {

    private UserDTO userDTO;
    private PlaceDTO placeDTO;
}
