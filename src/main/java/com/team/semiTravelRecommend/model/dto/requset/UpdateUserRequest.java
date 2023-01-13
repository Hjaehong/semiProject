package com.team.semiTravelRecommend.model.dto.requset;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UpdateUserRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String userId;
    @NotBlank
    private String email;
}
