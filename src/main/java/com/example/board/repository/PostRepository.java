package com.example.board.repository;

import com.example.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository 덕분에 기본 CRUD 메서드(저장, 조회, 삭제 등)가 자동으로 제공됨
}