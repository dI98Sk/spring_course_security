package com.skakundima.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //
        //
        // User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        //Часть отвечающая за хранения юзера к памяти программы(для теста)
        /*User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("dima").password("dima").roles("employee"))
                .withUser(userBuilder.username("elena").password("elena").roles("hr"))
                .withUser(userBuilder.username("ivan").password("ivan").roles("manager","hr"));
    */

        auth.jdbcAuthentication().dataSource(dataSource); // указываем куда обращаться за логин/пароль
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("employee", "hr", "manager")
                .antMatchers("/hr_info").hasRole("hr")
                .antMatchers("manager_info/**").hasRole("manager")
                .and().formLogin().permitAll(); //означает что форма логина и пароля будет запрашиваться у всех
    }


}
