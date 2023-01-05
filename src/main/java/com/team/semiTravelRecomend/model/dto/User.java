package com.team.semiTravelRecomend.model.dto;


import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User{

    private Long userNo;
    private String userId;
    private String userName;
    private String userPwd;
    private String email;
    private String role;

    public void updateUser(String email, String userName, String userPwd) {
        this.userId = email;
        this.userName = userName;
        this.userPwd = userPwd;
    }

}
