package com.jojoldu.book.springboot.domain.posts;


import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//엔티티 클래스에는 절대 Setter를 추가하지 않는다
@Getter
@NoArgsConstructor//기본 생성자 자동 추가
@Entity//테이블과 링크될 클래스 - 언더스코어 네이밍으로 테이블 이름 매칭 SalesManger.java -> sales_manager_table
public class Posts extends BaseTimeEntity {//실제 DB테이블과 매칭될 클래스(Entity 클래스)-> DB데이터에 작업할 경우 직접 쿼리 수정 대신 Entity클래스 수정을 통해 작업
    @Id//Primary Key를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//PK는 Long타입의 Auto_increment를 추천, 유니크키나, 복합키로 잡지 말자

    @Column(length = 500, nullable = false)//테이블의 칼럼을 나타내며, 선언안해도 클래스의 필드는 모두 칼럼이 됨, 옵션 있을때 어노테이션 선언
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder//해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
