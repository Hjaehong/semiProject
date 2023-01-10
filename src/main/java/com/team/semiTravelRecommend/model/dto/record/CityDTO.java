package com.team.semiTravelRecommend.model.dto.record;

import lombok.Data;

@Data
public class CityDTO {

    private String cityCode;
    private String cityName;
    private String badgeImg;
    private LocationDTO locationDTO;

}
