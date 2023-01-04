package com.team.semiTravelRecomend.controller;

import com.team.semiTravelRecomend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecomend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecomend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecomend.model.dto.response.DeleteUserResponse;
import com.team.semiTravelRecomend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecomend.model.dto.response.SaveUserResponse;
import com.team.semiTravelRecomend.model.dto.response.UpdateUserResponse;
import com.team.semiTravelRecomend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    //회원 저장
    @PostMapping
    public ResponseEntity<SaveUserResponse> save(@RequestBody @Valid SaveUserRequest saveUserRequest) {
        SaveUserResponse saveUserResponse = userService.save(saveUserRequest);

        return new ResponseEntity<>(saveUserResponse, HttpStatus.CREATED);
    }

    //회원 수정
    @PutMapping("/{userNo}")
    public ResponseEntity<UpdateUserResponse> update(@PathVariable Long userNo,
                                                     @RequestBody UpdateUserRequest updateUserRequest) {
        UpdateUserResponse updateUserResponse = userService.update(userNo, updateUserRequest);

        return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
    }

    //회원 삭제
    @DeleteMapping("/{userNo}")
    public ResponseEntity<DeleteUserResponse> delete(@PathVariable Long userNo) {
        DeleteUserResponse deleteUserResponse = userService.delete(userNo);

        return new ResponseEntity<>(deleteUserResponse, HttpStatus.OK);
    }

    //회원전체조회
    @GetMapping
    public ResponseEntity<List<SaveUserResponse>> findUsers() {
        List<SaveUserResponse> users = userService.findUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //회원id조회
    @GetMapping("/{userNo}")
    public ResponseEntity<SaveUserResponse> findById(@PathVariable Long userNo) {
        SaveUserResponse saveUserResponse = userService.findUser(userNo);

        return new ResponseEntity<>(saveUserResponse, HttpStatus.OK);
    }


    //로그인
    @PostMapping("/login") // http://localhost:8080/api/users/login  -> loginUserRequest(userId, password)
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest loginUserRequest,
                                                   HttpServletRequest request) {
        LoginUserResponse userResponse = userService.login(loginUserRequest);

        //session 생성.
        HttpSession session = request.getSession();// 새로운 세션을 생성한다.
        session.setAttribute("loginUser", userResponse);

        return new ResponseEntity(userResponse, HttpStatus.OK);
    }
}