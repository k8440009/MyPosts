package com.board.myPosts.web;

import com.board.myPosts.service.PostsService;
import com.board.myPosts.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final Log log = LogFactory.getLog(PostsApiController.class);
    private final PostsService postsService;

    @GetMapping("/api/v1/posts/{id}") // 조회
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api")
    public List<PostsListResponseDto> findAllDesc() {
        return postsService.findAllDesc();
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
