package com.team.semiTravelRecomend.model.dto.response;


import com.team.semiTravelRecomend.model.dto.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginUserResponse {

    private Long userNo;
    private String userId;

    public LoginUserResponse(User user) {
        this.userNo = user.getUserNo();
        this.userId = user.getUserId();
    }
}
