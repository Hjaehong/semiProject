package com.team.semiTravelRecommend.model.dto.requset;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class DeleteUserRequest {

    @NotBlank(message = "탈퇴하기 위해서는 이메일을 작성해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
}
