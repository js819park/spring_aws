package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//Dao라고 불리는 DB layer 접근자를 JPA에선 Repository라고 부르며 인터페이스로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {//Posts 클래스로 Database를 접근하게 해줄 JpaRepository- Dao(DB LAYER 접근자)
//인터페이스 생성후, JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD메소드가 자동으로 생성됨
    //Entity클래스는 기본 Entity Repository와 항상 함계 위치하여야 한다.

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
