package com.jimenahernando.entrega_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                (requests) -> requests
                        .antMatchers("/hola").permitAll()
                        .antMatchers("/puerto").hasRole("USER")
                        .antMatchers("/api/**").hasRole("USER")
                        .anyRequest().authenticated()
        );
        http.formLogin();
        http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("jhernando").password(new BCryptPasswordEncoder().encode("jhernando")).roles("USER").and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin1234")).roles("USER", "ADMIN");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

}
