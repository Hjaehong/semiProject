package com.team.semiTravelRecomend.model.dto.response;

import com.team.semiTravelRecomend.model.dto.UserVO;
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
