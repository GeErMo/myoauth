package com.ynbwjf.myoauth.config;

import com.ynbwjf.myoauth.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/home","/verificationCode").permitAll()//指定哪些资源不被拦截
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/loginPage").loginProcessingUrl("/login").permitAll().defaultSuccessUrl("/index",true).successHandler(myLoginSuccessHandler()).failureUrl("/loginPage")
                .and()
                .logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/loginPage").invalidateHttpSession(true);
        http.addFilterBefore(new VerificationCodeFilter("/login"),UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider());
        auth.userDetailsService(myUserDetailsService()).passwordEncoder(myPasswordEncode());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**","/js/**");
    }


    @Bean
    protected AuthenticationProvider myAuthenticationProvider(){
        return new CustomAuthenticationProvider();
    }
    @Bean
    protected UserDetailsService myUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    protected BCryptPasswordEncoder myPasswordEncode(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected LoginSuccessHandler myLoginSuccessHandler(){
        return new LoginSuccessHandler();
    }








}
