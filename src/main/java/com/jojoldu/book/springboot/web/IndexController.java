package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
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
    public String index(Model model, @LoginUser SessionUser user){//Model - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts" ,postsService.findAllDesc());//postService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달

       // SessionUser user = (SessionUser) httpSession.getAttribute("user");//로그인 성공 시 세션에 SessionUser를 저장하도록 구성,
        //로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다.


        if(user!= null){//세션에 저장된 값이 있을 떄만 userName으로 등록하고 없으면 로그인 버튼이 보이게 된다.
            model.addAttribute("userName",user.getName());
        }
        return "index";//View Resolver가 index.mustache를 처리
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }//posts/save를 호출하면 posts-save.mustache를호출

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);//이 attributName에 맞춰 머스태치의 객체명이랑 돌아감, 이 부분 posts로 써서 2시간 날림;;
        return "posts-update";
    }

}


