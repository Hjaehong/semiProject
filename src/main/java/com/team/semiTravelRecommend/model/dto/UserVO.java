package com.team.semiTravelRecommend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String nickname; // 임시
    private List<String> regions;


    public void updateUser(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public void deleteUser(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
