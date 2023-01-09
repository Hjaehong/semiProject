package com.team.semiTravelRecommend.model.dto.requset;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class LoginUserRequest {

    @NotBlank(message = "아이디는 필수 값입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수 값입니다.")
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            //message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String userPwd;
}
