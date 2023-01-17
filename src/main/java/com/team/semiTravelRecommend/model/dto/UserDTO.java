package com.team.semiTravelRecommend.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private int userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String Email;
    private String nickname;
    private String status;
    private int profileImg;
    private String intro;
    private TagDTO tagDTO;

}
