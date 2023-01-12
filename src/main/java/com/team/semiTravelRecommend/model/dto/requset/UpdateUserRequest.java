package com.team.semiTravelRecommend.model.dto.requset;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateUserRequest {
    private String userName;
    private String userPwd;
    private String email;
}
