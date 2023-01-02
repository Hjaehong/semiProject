package com.team.semiTravelRecomend.model.dto.response;


import com.team.semiTravelRecomend.model.dto.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeleteUserResponse {

    private String deleteUserName;

    public DeleteUserResponse(User deletedUser) {
        this.deleteUserName = deleteUserName;
    }
}
