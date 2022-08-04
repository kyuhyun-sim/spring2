package com.sparta.springcore.service;

import com.sparta.springcore.dto.LoginRequestDto;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginConfirmService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean loginConfirm(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        Optional<User> userNameFound = userRepository.findByUsername(username);

        if (!userNameFound.isPresent()) {
            throw new IllegalArgumentException("아이디가 존재하지 않습니다.");
        }


        if (!passwordEncoder.matches(requestDto.getPassword(), userNameFound.get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 존재하지 않습니다.");

        }else {
            return true;
        }
    }
}
