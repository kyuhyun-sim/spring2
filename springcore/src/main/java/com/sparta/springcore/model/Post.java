package com.sparta.springcore.model;

import com.sparta.springcore.dto.PostCreatRequestDto;
import com.sparta.springcore.repository.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity(name = "posts") // DB 테이블 역할을 합니다.
public class Post extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Long userId;




    public Post(String title, String content, String author, Long userId) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.userId = userId;
    }

    public Post(PostCreatRequestDto requestDto, String author, Long userId){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = author;
        this.userId = userId;
    }
}