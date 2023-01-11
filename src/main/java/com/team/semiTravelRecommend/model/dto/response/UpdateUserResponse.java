package com.team.semiTravelRecommend.model.dto.response;

import com.team.semiTravelRecommend.model.dto.UserVO;
import lombok.Data;

@Data
public class UpdateUserResponse {

    private Long userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
    private String role;

    public UpdateUserResponse(UserVO updatedUser) {
        this.userPwd = updatedUser.getUserPwd();
        this.userName = updatedUser.getUserName();
        this.email = updatedUser.getEmail();

    }
}
