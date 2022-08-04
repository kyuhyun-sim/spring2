package com.sparta.springcore.service;

import com.sparta.springcore.dto.PostCreatRequestDto;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.PostRepository;
import com.sparta.springcore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostCreatService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;



    public Post createPost(PostCreatRequestDto requestDto, Long userId) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기

        Optional<User> userIdFound =  userRepository.findById(userId);
        if (!userIdFound.isPresent()) {
            throw new IllegalArgumentException("아이디가 존재하지 않습니다.");
        }
        String author = userIdFound.get().getUsername();
        System.out.println(author);
        Post post = new Post(requestDto, author, userId);

        postRepository.save(post);

        return post;
    }
}
