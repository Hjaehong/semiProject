package com.team.semiTravelRecommend.model.dto.response;

import com.team.semiTravelRecommend.model.dto.UserVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeleteUserResponse {

    private String deleteUserName;

    public DeleteUserResponse(UserVO deletedUser) {
        this.deleteUserName = deletedUser.getUserName();
    }
}
