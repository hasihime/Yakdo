package com.noyak.yakdo.springboot.config.auth;


import com.noyak.yakdo.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // h2-console화면을 사용하기 위해 해당 옵션들 disable
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests() // URL 별 권한 관리를 설정하는 옵션의 시작점 - antMatchers 옵션 사용 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**",
                        "/review/**", "/pharmacy/**", "/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL들 모두 인증된 사용자(로그인한 사람)들에게만 허용함
            .and()
                .logout()
                    .logoutSuccessUrl("/") // 로그아웃 성공시 /주소로 이동
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService);
    }
}
