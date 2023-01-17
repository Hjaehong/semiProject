package com.team.semiTravelRecommend.model.dto;

import lombok.Data;

@Data
public class FileDTO {

    private int fileNo;
    private long fileSize;
    private String originName;
    private String changeName;
    private String imgPath;
}
