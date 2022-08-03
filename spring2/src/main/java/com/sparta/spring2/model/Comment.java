package com.sparta.spring2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity(name = "comment") // DB 테이블 역할을 합니다.
public class Comment extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long postId;



    public Comment(String username, String contents, Long userId, Long postId) {
        this.author = username;
        this.contents = contents;
        this.userId = userId;
        this.postId = postId;
    }
}