package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 글 저장
    public Post save(Post post) {
        return postRepository.save(post);
    }
}