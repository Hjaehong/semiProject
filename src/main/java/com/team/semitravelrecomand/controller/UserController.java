package com.team.semitravelrecomand.controller;


import com.team.semitravelrecomand.dto.request.SaveUserRequest;
import com.team.semitravelrecomand.dto.response.SaveUserResponse;
import com.team.semitravelrecomand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SaveUserResponse> signUp(@RequestBody @Valid SaveUserRequest request) {
        SaveUserResponse saveUserResponse = userService.signUp(request);
        return new ResponseEntity<>(saveUserResponse, HttpStatus.CREATED);
    }
}
