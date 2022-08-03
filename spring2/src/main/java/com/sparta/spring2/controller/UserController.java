package com.sparta.spring2.controller;

import com.sparta.spring2.dto.SignupRequestDto;
import com.sparta.spring2.servic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //회원가입 post API
    @PostMapping("/api/member/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    //로그인 get api

}
