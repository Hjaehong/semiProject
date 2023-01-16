package com.team.semiTravelRecommend.model.dto;
import com.team.semiTravelRecommend.model.dto.recommend.PlaceDTO;
import com.team.semiTravelRecommend.model.dto.record.UserDTO;
import lombok.Data;

@Data
public class BookmarkDTO {

    private UserDTO userDTO;
    private PlaceDTO placeDTO;
}