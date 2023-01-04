package com.team.semiTravelRecommend.model.dto.record;

import lombok.Data;

@Data
public class FileDTO {

    private int fileNo;
    private int fileSize;
    private String originName;
    private String changeName;
    private String imgPath;
}
