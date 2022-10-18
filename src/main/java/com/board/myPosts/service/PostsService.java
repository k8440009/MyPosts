package com.board.myPosts.service;

import com.board.myPosts.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    public PostsResponseDto findById (Long id) {
        return new PostsResponseDto();
    }
}
