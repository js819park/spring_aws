package com.jojoldu.book.springboot.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestsDto {//JPA 영속성 컨텍스트 때문에 트랜젝션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태,
    private String title;//이상태에서 해당 데이터 값을 변경하면 트랜잭션이 끝나느 시점에 해당 테이블 변경분을 반영한다. 즉 Entity 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요 없음
    private String content;

    @Builder
    public PostsUpdateRequestsDto(String title, String content){
        this.title = title;
        this.content = content;
    }


}
