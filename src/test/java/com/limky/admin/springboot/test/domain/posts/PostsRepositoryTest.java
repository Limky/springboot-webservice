package com.limky.admin.springboot.test.domain.posts;

import com.limky.admin.springboot.domain.posts.Posts;
import com.limky.admin.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void posts_load(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 posts에 insert/update쿼리를 수행
        //id 값이 있다면 update 없다면 insert
        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("setupman82@gmail.com")
                                    .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }

    @Test
    public void BaseTimeEntity_save(){

        //given
        LocalDateTime now = LocalDateTime.of(2019,2,15,0,0,0);
        postsRepository.save(Posts.builder()
                                    .title("title")
                                    .content("content")
                                    .author("setupman82@gmail.com")
                                    .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate="+posts.getCreateDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

        System.out.println(">>>>>>>>>> end >>>>>>>>>>");

    }


}
