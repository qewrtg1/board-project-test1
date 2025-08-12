package com.example.board.controller;

import com.example.board.domain.Post;
import com.example.board.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 헬스체크용 간단한 엔드포인트 (데이터베이스 연결 없이도 작동)
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK";
    }

    // 글 작성 폼 화면
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("post", new Post());
        return "form";
    }

    // 글 작성 처리
    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/posts";
    }

    // 글 목록 조회
    @GetMapping("")
    public String listPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "list";
    }

    // 글 상세 조회
    @GetMapping("/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "detail";
    }

    // 글 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "edit";
    }

    // 글 수정 처리
    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post form) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setWriter(form.getWriter());
        postRepository.save(post);
        return "redirect:/posts/" + id;
    }

    // 글 삭제 처리
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/posts";
    }
}