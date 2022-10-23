package com.board.myPosts.web;

import com.board.myPosts.domain.posts.Posts;
import com.board.myPosts.domain.posts.PostsRepository;
import com.board.myPosts.web.dto.PostsFindRequestDto;

import com.board.myPosts.web.dto.PostsResponseDto;
import com.board.myPosts.web.dto.PostsSaveRequestDto;
import com.board.myPosts.web.dto.PostsUpdateRequestDto;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// @ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;

    private StringBuilder sb = new StringBuilder();
    private String localHostUrl = "http://localhost:";
    private String path = "/api/v1/posts";
    private Long id = 1L;
    private String title = "title";
    private String content = "content";
    private String author = "author";

    private String title2 = "title2";
    private String content2 = "content2";
    @BeforeEach
    public void clean() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_조회() throws Exception {
        Posts savedPosts = postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                .build());
        Long findId = savedPosts.getId();
        // url
        sb.setLength(0);
        sb.append(localHostUrl).append(port).append("/api/v1/posts/1");

        System.out.println(">>>>>>>>>>>>>>>>> findId=" + findId);
        System.out.println("URL=" + sb.toString());
        // when
        ResponseEntity <PostsResponseDto> responseEntity = restTemplate.getForEntity(sb.toString(), PostsResponseDto.class);
        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_등록() throws Exception{
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        sb.setLength(0);
        sb.append(localHostUrl).append(port).append(path);

        String url = sb.toString();
        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정() {
        Posts savedPosts = postsRepository
                .save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                        .build());

        Long id = savedPosts.getId();

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(title2)
                .content(content2)
                .build();

        sb.setLength(0);
        sb.append(localHostUrl).append(port).append("/api/v1/posts/").append(id);
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(sb.toString(), HttpMethod.POST, requestEntity, Long.class);
        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title2);
        assertThat(all.get(0).getContent()).isEqualTo(content2);

    }
}
