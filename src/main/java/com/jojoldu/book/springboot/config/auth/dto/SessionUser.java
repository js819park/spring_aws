package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//인증된 사용자 정보만 필요하여 3개만 필드로 선언
@Getter
public class SessionUser implements Serializable {//USER가 엔티티라 다른 엔티티와 관계가 형성될 수 있으니 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이
    //운영 및 유지보수에 유리하다.
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
