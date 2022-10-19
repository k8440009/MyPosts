package com.board.myPosts.web;

import com.board.myPosts.domain.posts.Posts;
import com.board.myPosts.domain.posts.PostsRepository;
import com.board.myPosts.web.dto.PostsFindRequestDto;

import com.board.myPosts.web.dto.PostsSaveRequestDto;
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

import java.util.List;

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
    private String path2 = "/api/v1/posts/";
    private Long id = 1L;
    private String title = "title";
    private String content = "content";
    private String author = "author";

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

        System.out.println(">>>>>>>>>>>>>>>>> findId=" + findId);

        PostsFindRequestDto requestDto = PostsFindRequestDto
                .builder()
                .id(findId)
                .build();
        // url
        sb.setLength(0);
        sb.append(localHostUrl).append(port).append(path).append(findId);

        // when
//        ResponseEntity <Long> responseEntity = restTemplate.getForEntity(sb.toString(), Long.class);
        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
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
}
