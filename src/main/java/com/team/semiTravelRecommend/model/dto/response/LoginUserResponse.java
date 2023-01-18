package com.team.semiTravelRecommend.model.dto.response;


import com.team.semiTravelRecommend.model.dto.UserVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginUserResponse {

    private Long userNo;
    private String userId;
    private String email;

    public LoginUserResponse(UserVO user) {
        this.userNo = user.getUserNo();
        this.userId = user.getUserId();
        this.email = user.getEmail();
    }
}
