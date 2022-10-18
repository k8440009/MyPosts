package com.board.myPosts.service;

import com.board.myPosts.domain.posts.Posts;
import com.board.myPosts.domain.posts.PostsRepository;
import com.board.myPosts.web.dto.PostsResponseDto;
import com.board.myPosts.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음. id=" + id));
        return new PostsResponseDto(entity);

    }
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
