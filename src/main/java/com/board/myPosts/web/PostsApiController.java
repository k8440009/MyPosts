package com.board.myPosts.web;

import com.board.myPosts.service.PostsService;
import com.board.myPosts.web.dto.PostsResponseDto;
import com.board.myPosts.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/api/v1/posts/{id}") // 조회
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
}
