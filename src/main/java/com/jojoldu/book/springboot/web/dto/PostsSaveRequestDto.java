package com.jojoldu.book.springboot.web.dto;


import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor//Controller와 Service에서 사용할 Dto클래스 생성 -> View계층과 DB계층을 확실히 분리 -> 이 클래스는 View계층에서 받아서 엔티티에 저장은 request
public class PostsSaveRequestDto {//Entity클래스와 거의 유사한 형태임에도 Dto클래스를 추가로 생성 ->View를 위한 클래스라 자주 변경이 필요 -> Entity클래스를 절대로 Request/Response클래스로 사용하지 않기 위해서 만듦
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
