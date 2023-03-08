package com.team.semiTravelRecommend.model.dto;

import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserResponse {

    private Long userNo;
    private String userName;
    private String email;
    private String password;
    private String userId;

    public UserResponse(UserVO userVO) {
        this.userNo = userVO.getUserNo();
        this.userName = userVO.getUserName();
        this.email = userVO.getEmail();
        this.password = userVO.getUserPwd();
        this.userId = userVO.getUserId();
    }

    public UserResponse(LoginUserResponse loginUser) {
        this.userId = loginUser.getUserId();
        this.email = loginUser.getEmail();
        this.userNo = loginUser.getUserNo();
    }
}
