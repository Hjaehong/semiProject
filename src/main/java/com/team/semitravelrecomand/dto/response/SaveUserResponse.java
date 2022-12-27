package com.team.semitravelrecomand.dto.response;

import com.team.semitravelrecomand.domain.User;
import com.team.semitravelrecomand.domain.UserRole;
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
    private UserRole role;

    public SaveUserResponse(User savedUser) {
        this.userNo = savedUser.getUserNo();
        this.userId = savedUser.getUserId();
        this.userPwd = savedUser.getUserPwd();
        this.userName = savedUser.getUserName();
        this.email = savedUser.getEmail();
        this.nickname = savedUser.getNickname();
        this.role = savedUser.getRole();
    }
}
