package com.canesblack.spring_project1.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.io.IOException;

@Configuration
// SpringSecurity 기능을 사용하려면 이 어노테이션을 적어야함
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
        // 스프링시큐리티 기능을 사용하고자 할때 이 메소드안에 작성
        http.formLogin(
                login->login.loginPage("/loginPage") // url을 작성해서 로그인제이지로 이동할때
                        .loginProcessingUrl("/login")
                        .failureUrl("/loginPage?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                        )
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                // 로그인이 성공했을때 우리가 특별기능을 넣고 싶을때(세션, 권한기능)
                HttpSession session = request.getSession(); // 세션 기능을 가지고 온것
                boolean isManager = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority ->
                                grantedAuthority.getAuthority().equals("ADMIN") ||
                                grantedAuthority.getAuthority().equals("MANAGER"));
                if(isManager) {
                    session.setAttribute("MANAGER", true);
                }
                session.setAttribute("username", authentication.getName());
                session.setAttribute("isAuthenticatied", true);
                // request.getContextPath() => localhost:8080
                response.sendRedirect(request.getContextPath() + "/");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }
}
