package com.team.semiTravelRecommend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserVO {

    private Long userNo;
    private String userId;
    private String userName;
    private String userPwd;
    private String email;
    private String role;

    public void updateUser(String userName, String email, String userPwd) {
        this.userId = email;
        this.userName = userName;
        this.userPwd = userPwd;
    }
}
