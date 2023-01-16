package com.team.semiTravelRecommend.model.dto.response;

import com.team.semiTravelRecommend.model.dto.UserVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SaveUserResponse {

    private Long userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
    private String nickname;
    private String role;

    public SaveUserResponse(UserVO savedUser) {
        this.userNo = savedUser.getUserNo();
        this.userId = savedUser.getUserId();
        this.userPwd = savedUser.getUserPwd();
        this.userName = savedUser.getUserName();
        this.email = savedUser.getEmail();
        // 추가
        this.nickname = savedUser.getNickname();

    }
}
