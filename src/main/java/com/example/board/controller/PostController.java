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

    // 헬스체크용 간단한 엔드포인트
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK";
    }

    // 글 작성 폼 화면
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("post", new Post());  // 빈 Post 객체를 넘김
        return "form";  // templates/form.html로 이동
    }

    // 글 작성 처리
    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/posts";  // 저장 후 글 목록 페이지로 이동
    }

    // 루트 경로 - 글 목록으로 리다이렉트
    @GetMapping("/")
    public String root() {
        return "redirect:/posts";
    }

    // 글 목록 조회
    @GetMapping("")
    public String listPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "list";  // templates/list.html로 이동
    }

    // 글 상세 조회
    @GetMapping("/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "detail";  // templates/detail.html로 이동
    }
}