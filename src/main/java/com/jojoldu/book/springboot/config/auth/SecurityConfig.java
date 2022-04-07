package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()//h2-console화면을 사용하려고 해당 옵션들 disable
                .and()
                .authorizeRequests()//URL별 권한 관리 설정 옵션의 시작점, 이게 선언되어야 antMatchers사용 가능
                .antMatchers("/","/css/**", "/images/**",//권한 관리 대상 지정, URL, HTTP메소드 별로 관리 가능 ,"/"등 지정된 URL들은
                        "/js/**", "h2-console/**").permitAll()//permitaAll()옵션으로 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())//"api/v1/**:주소를 가진 API는 USER권한을 가진 사람만 가능
                .anyRequest().authenticated()//설정된 값 이외의 나머지 URL,authenticated()를 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용되게 함
                .and()
                .logout()
                .logoutSuccessUrl("/")//로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공하면 /주소 로 이동
                .and()
                .oauth2Login()//OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint()//OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                .userService(customOauth2UserService);
        //소셜 로그인 성공 시 후속 조치를 진행할 UserService인터페이스의 구현체를 등록, 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고 자 하는 기능 명시
    }

}