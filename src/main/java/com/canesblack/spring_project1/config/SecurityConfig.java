package com.canesblack.spring_project1.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

@Configuration
// SpringSecurity 기능을 사용하려면 이 어노테이션을 적어야함
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 스프링시큐리티 기능을 사용하고자 할때 이 메소드안에 작성
        http
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(authz -> authz.requestMatchers("/","/loginPage","/logout","/noticeCheckPage","/registerPage","/menu/all")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/login","/register").permitAll()
                        .requestMatchers("/resources/**","/WEB-INF/**").permitAll()
                        .requestMatchers("/noticeAddPage","/noticeModifyPage").hasAnyAuthority("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.POST, "/menu/add").hasAnyAuthority("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.POST, "/menu/update").hasAnyAuthority("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.POST, "/menu/delete").hasAnyAuthority("ADMIN","MANAGER")
                        .anyRequest().authenticated()   // // 로그인을 해야지만 접근이 가능하게끔 그렇기 때문에 로그인페이지로 자동 이동됩니다
                )


        .formLogin(
                login->login.loginPage("/loginPage") // url을 작성해서 로그인제이지로 이동할때
                .loginProcessingUrl("/login")
                .failureUrl("/loginPage?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                )

        .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")  // 로그아웃 성공후 이 url로 리다이렉팅
                .invalidateHttpSession(true) // 세션무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
                .permitAll() // 위 기능을 수행하려면 이 메서드 실행
                );

        return http.build();    // 최종 http에 적용시킬때 사용하는 메서드
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
                // 세션에다가 로그인한 아이디를 저장한다.
                session.setAttribute("username", authentication.getName());
                // 세션에다가 로그인 여부를 저장
                session.setAttribute("isAuthenticated", true);
                // request.getContextPath() => localhost:8080
                response.sendRedirect(request.getContextPath() + "/");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "https://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

