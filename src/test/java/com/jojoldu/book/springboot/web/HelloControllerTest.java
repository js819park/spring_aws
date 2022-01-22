package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)//테스트 진행할 때 JUnit에 내장된 실행자 말고 SpringRunner라는 스프링 실행자를 사용, 스프링부트 테스트와 JUnit사이의 연결자 역활
@WebMvcTest(controllers = HelloController.class,//WEBMVC에 집중할 수 있는 어노테이션, 선언하면 @Controller, @ControllerAdivce등 사용 가능
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest {

    @Autowired//빈 주입 받고
    private MockMvc mvc;//웹 API테스트 할 때 사용, 이 클래스로 HTTP, GET, POST등에 대한 API테스트 가능

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))//MockMvc를 통해 /hello 주소로 HTTP GET 요쳥을 함
                .andExpect(status().isOk())//202,404,500등 상태 검증 , 여기선 200(OK)검증
                .andExpect(content().string(hello));//컨트롤러에서 "hello"를 리턴하니까 이값이 맞는지 검ㅔ
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;


        mvc.perform(
                get("/hello/dto")
                        .param("name",name)//param - API 테스트할 때 사용될 요청 파라미터 설정, String만 허용, 숫자 날짜등도 다 문자열로 변경해서 사용
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))//is()까지만 치고 static import static org.hamcrest.Matchers.is
                .andExpect(jsonPath("$.amount",is(amount)));// $를 기준으로 필드명 명시

    }
}
