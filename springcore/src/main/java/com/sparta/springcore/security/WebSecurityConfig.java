package com.sparta.springcore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true) // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF protection 을 비활성화
        http.csrf().disable();

        http
                .authorizeHttpRequests((authz) -> authz
                        // image 폴더를 login 없이 허용
                        .antMatchers("/images/**").permitAll()
                        // css 폴더를 login 없이 허용
                        .antMatchers("/css/**").permitAll()
                        // 회원 관리 처리 API 전부를 login 없이 허용
                        .antMatchers("/api/**").permitAll()
                        // 어떤 요청이든 '인증'
                        .anyRequest().authenticated()
                )
                // [로그인 기능]
                .formLogin()
                // 로그인 View 제공 (GET /user/login)
                .loginPage("/user/login")
                // 로그인 처리 (POST /user/login)
                .loginProcessingUrl("/user/login")
                // 로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")
                // 로그인 처리 후 실패 시 URL
                .failureUrl("/user/login?error")
                .permitAll()
                .and()
                // [로그아웃 기능]
                .logout()
                // 로그아웃 처리 URL
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling();

        return http.build();
    }
}

//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.token.TokenService;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//public class WebSecurityConfig {
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/h2-console/**", "/user/**");
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/user/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//
//                .formLogin()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//        return http.build();
//    }
//
//}

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) {
//// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//// 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
//        http.csrf()
//                .ignoringAntMatchers("/user/**");
//
//        http.authorizeRequests()
//// image 폴더를 login 없이 허용
//                .antMatchers("/images/**").permitAll()
//// css 폴더를 login 없이 허용
//                .antMatchers("/css/**").permitAll()
//// 회원 관리 처리 API 전부를 login 없이 허용
//                .antMatchers("/user/**").permitAll()
//// 그 외 어떤 요청이든 '인증'
//                .anyRequest().authenticated()
//                .and()
//// 로그인 기능
//                .formLogin()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
//// 로그아웃 기능
//                .logout()
//                .permitAll();
//    }
//}
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//// image 폴더를 login 없이 허용
//                .antMatchers("/images/**").permitAll()
//// css 폴더를 login 없이 허용
//                .antMatchers("/css/**").permitAll()
//// 어떤 요청이든 '인증'
//                .anyRequest().authenticated()
//                .and()
//// 로그인 기능 허용
//                .formLogin()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
//// 로그아웃 기능 허용
//                .logout()
//                .permitAll();
//    }
//}
//
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////
////@Configuration
////@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
////public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////
////        http.authorizeRequests()
////// 어떤 요청이든 '인증'
////                .anyRequest().authenticated()
//////                .and()
//////// 로그인 기능 허용
//////                .formLogin()
//////                .defaultSuccessUrl("/")
//////                .permitAll()
////                .and()
////// 로그인 기능 허용
////                .formLogin()
////                .loginPage("/user/login")
////                .defaultSuccessUrl("/")
////                .failureUrl("/user/login?error")
////                .permitAll()
////                .and()
////// 로그아웃 기능 허용
////                .logout()
////                .permitAll();
////    }
////}