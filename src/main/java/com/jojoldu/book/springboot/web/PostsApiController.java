package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor//생성자로 Bean객체를 받게 하는게 @Autowired와 같은 효과를 내는데 룸북으로 대신 생성했음-> 클래스의 의존성 관계까 변경될 때마다 생성자 코드를 수정 안해도 트
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){//View에서 받아온 Dto를
        return postsService.save(requestDto);//서비스 계층으로 넘겨줌
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestsDto requestDto){//pathVariable -URL경로에 변수를 넣어줌, id값으로 넘어온 변수를 id에 바인딩,
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
