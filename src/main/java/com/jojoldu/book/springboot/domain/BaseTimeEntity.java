package com.jojoldu.book.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class)//Auditing기능 포함
public abstract class BaseTimeEntity {//entity들의 createDate, modifiedDate를 자동으로 관리

    @CreatedDate//Entity생성 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate//조회한 Entity값을 변경할 때 시간 자동 저장
    private LocalDateTime modifiedDate;



}
