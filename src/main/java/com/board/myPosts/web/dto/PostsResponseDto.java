package com.board.myPosts.web.dto;

import com.board.myPosts.domain.posts.Posts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts Entity) {
        this.id = Entity.getId();
        this.title = Entity.getTitle();
        this.content = Entity.getContent();
        this.author = Entity.getAuthor();
    }

}
