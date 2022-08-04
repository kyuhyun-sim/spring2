package com.sparta.springcore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springcore.dto.ResponseDto;
import com.sparta.springcore.dto.SignupRequestDto;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.UserRepository;
import com.sparta.springcore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 post API
    @PostMapping("/api/member/signup")
    public ResponseDto<?> registerUser(@RequestBody SignupRequestDto requestDto) {
        return ResponseDto.success(userService.registerUser(requestDto));
    }

    //로그인 get api

}
