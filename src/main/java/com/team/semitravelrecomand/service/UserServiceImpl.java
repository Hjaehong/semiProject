package com.team.semitravelrecomand.service;

import com.team.semitravelrecomand.domain.User;
import com.team.semitravelrecomand.domain.UserRole;
import com.team.semitravelrecomand.dto.request.SaveUserRequest;
import com.team.semitravelrecomand.dto.response.SaveUserResponse;
import com.team.semitravelrecomand.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public SaveUserResponse signUp(SaveUserRequest request) {
        User user = User.builder()
                .userId(request.getUserId())
                .userPwd(BCrypt.hashpw(request.getUserPwd(), BCrypt.gensalt()))
                .userName(request.getUserName())
                .email(request.getEmail())
                .nickname(request.getNickname())
                .role(UserRole.USER)
                .build();

       User savedUser = userRepository.save(user);

        SaveUserResponse saveUserResponse = new SaveUserResponse(savedUser);
        return saveUserResponse;
    }
}
