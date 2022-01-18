package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//Dao라고 불리는 DB layer 접근자를 JPA에선 Repository라고 부르며 인터페이스로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {


    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
