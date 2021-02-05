package com.limky.admin.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//Entity 클래스와 위치는 같아야 함
//Dao 개념
//<Entity 클래스, PK 타입>
//CRUD 메서드 자동으로 생성됨
public interface PostsRepository extends JpaRepository<Posts, Long> {


}