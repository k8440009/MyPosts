package com.board.myPosts.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    String title;
    String content;

    @Builder
    public PostsUpdateRequestDto (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
