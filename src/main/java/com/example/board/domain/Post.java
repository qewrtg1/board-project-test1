package com.example.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 ID 증가
    private Long id;

    private String title;  // 글 제목

    @Column(columnDefinition = "TEXT")
    private String content;  // 글 내용

    private String writer;  // 작성자
}