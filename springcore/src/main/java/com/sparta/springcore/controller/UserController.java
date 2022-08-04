package com.sparta.springcore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springcore.dto.LoginRequestDto;
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
        if(requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
            return ResponseDto.success(userService.registerUser(requestDto));
        }
        else {return ResponseDto.fail("PASSWORD_MISMATCH","비밀번호가 일치하지 않습니다.");}

    }

    //로그인 get api

//    @PostMapping("/api/member/login")
//    public ResponseDto<?> loginUser(@RequestBody LoginRequestDto requestDto) {
//        if(requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
//            return ResponseDto.success(userService.registerUser(requestDto));
//        }
//        else {return ResponseDto.fail("PASSWORD_MISMATCH","비밀번호가 일치하지 않습니다.");}
//
//    }
}
