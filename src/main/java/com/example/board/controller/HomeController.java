package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // 루트 경로 헬스체크 (Railway용)
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "OK";
    }
}
