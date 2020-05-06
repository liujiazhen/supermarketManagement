package com.supermarket.management.config.security;

import com.supermarket.management.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //配置策略
        http// 允许网页iframe
                .headers().frameOptions().disable()
                .and().cors()
                .and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/js/**", "/layui/**", "/regeritUser", "/regerit","/getAllRole",".ttf",".woff",".woff2").permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage(securityProperties.getLoginUrl()).loginProcessingUrl(securityProperties.getLoginProcessingUrl())
                .usernameParameter("username")  //用户名的请求字段 默认为userName
                .passwordParameter("password")
                .permitAll().successHandler(loginSuccessHandler())
                .and()
                .logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .sessionManagement().maximumSessions(10).expiredUrl(securityProperties.getLoginUrl());
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                User userDetails = (User) authentication.getPrincipal();
                logger.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
