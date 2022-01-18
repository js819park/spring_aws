package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {//inedx.mustache를 View Resolver가 처리

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){//Model - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다. postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts" ,postsService.findAllDesc());
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);//이 attributName에 맞춰 머스태치의 객체명이랑 돌아감, 이 부분 posts로 써서 2시간 날림;;
        return "posts-update";
    }

}


