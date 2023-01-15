package com.team.semiTravelRecommend.model.dto;

import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponse {

    private String userName;
    private String email;
    private String password;
    private String userId;

    public UserResponse(UserVO userVO) {
        this.userName = userVO.getUserName();
        this.email = userVO.getEmail();
        this.password = userVO.getUserPwd();
        this.userId = userVO.getUserId();
    }

    public UserResponse(LoginUserResponse loginUser) {
        this.userId = loginUser.getUserId();
        this.email = loginUser.getEmail();
    }
}
