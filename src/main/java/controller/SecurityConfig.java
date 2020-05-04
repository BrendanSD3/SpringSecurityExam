/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author brend
 */




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//   
   

   
       @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.inMemoryAuthentication().withUser("bob")
          .password(("pass")).roles("SuperAdmin").and()
          .withUser("tom")
          .password(("pass")).roles("Admin")
               .and().withUser("mary")
          .password(("pass")).roles("User");
    }


       @Override
  protected void configure(HttpSecurity http) throws Exception {
    
  	http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
                        .antMatchers("*/product*/**").hasAnyRole("Admin","User","SuperAdmin")
                        .and()
  			.formLogin()
                        .loginProcessingUrl("/product/processlogin")
                        .defaultSuccessUrl("/product/viewall",true)
                        //.failureUrl("/login.jsp?error=true")
                        .and()
                        .logout()
                        .deleteCookies("JSESSIONID");
                        
       
        
  } 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    
    

}

