package com.team.semiTravelRecomend.model.dto.requset;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginUserRequest {

    private String userId;
    private String password;
}
