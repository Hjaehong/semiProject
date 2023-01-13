package com.team.semiTravelRecommend.model.dto.response;

import com.team.semiTravelRecommend.model.dto.UserVO;
import lombok.Data;

@Data
public class UpdateUserResponse {

    private Long userNo;
    private String userId;
    private String userName;
    private String email;

    public UpdateUserResponse(UserVO updatedUser) {
        this.userNo = updatedUser.getUserNo();
        this.userId = updatedUser.getUserId();
        this.userName = updatedUser.getUserName();
        this.email = updatedUser.getEmail();
    }
}
