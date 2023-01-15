package com.team.semiTravelRecommend.model.dto.requset;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UpdateUserRequest {

    @NotBlank(message = "수정할 이름을 적으세요")
    private String userName;
    @NotBlank(message = "수정할 아이디를 적으세요")
    private String userId;
    @NotBlank(message = "수정할 이메일을 적으세요")
    private String email;
}
