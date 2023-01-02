package com.team.semiTravelRecomend.model.dto.response;


import com.team.semiTravelRecomend.model.dto.User;
import com.team.semiTravelRecomend.model.dto.UserRole;
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
    private UserRole role;

    public SaveUserResponse(User savedUser) {
        this.userNo = savedUser.getUserNo();
        this.userId = savedUser.getUserId();
        this.userPwd = savedUser.getUserPwd();
        this.userName = savedUser.getUserName();
        this.email = savedUser.getEmail();
        this.role = savedUser.getRole();
    }
}
