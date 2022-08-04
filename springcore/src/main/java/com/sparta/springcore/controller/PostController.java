package com.sparta.springcore.controller;

import com.sparta.springcore.dto.PostCreatRequestDto;
import com.sparta.springcore.dto.ResponseDto;
import com.sparta.springcore.dto.SignupRequestDto;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.PostCreatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostCreatService postCreatService;

    @PostMapping("/api/auth/post")
    public ResponseDto<?> postCreate(@RequestBody PostCreatRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("나는"+userDetails.getUser());
        Long userId = userDetails.getUser().getId();

        return ResponseDto.success(postCreatService.createPost(requestDto, userId));
    }
}
