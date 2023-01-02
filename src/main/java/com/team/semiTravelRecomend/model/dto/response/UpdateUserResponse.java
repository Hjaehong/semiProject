package com.team.semiTravelRecomend.model.dto.response;


import com.team.semiTravelRecomend.model.dto.User;
import com.team.semiTravelRecomend.model.dto.UserRole;
import lombok.Data;

@Data
public class UpdateUserResponse {

    private Long userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
    private UserRole role;

    public UpdateUserResponse(User updatedUser) {
        this.userPwd = updatedUser.getUserPwd();
        this.userName = updatedUser.getUserName();
        this.email = updatedUser.getEmail();

    }
}
