package com.board.myPosts.web.dto;

import com.board.myPosts.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsFindRequestDto {
    private Long id;

    @Builder
    public PostsFindRequestDto(Long id) {
        this.id = id;
    }
}
