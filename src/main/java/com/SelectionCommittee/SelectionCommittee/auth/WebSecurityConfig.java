package com.SelectionCommittee.SelectionCommittee.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfig - configuration security
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    protected UserDetailsService myUserDetailsService;

    /**
     * Configuration where take UserDerailService and set password encoder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    /**
     * definition of access to url,
     * without registration can order to the main page, list of faculties, applications, registration and login pages
     * applicant can also order to send request and applicant info pages
     * admin can order to admin functions
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/faculties", "/request", "/register").permitAll()
                .antMatchers("/send_request", "/applicant").hasAuthority("applicant")
                .antMatchers(
                        "/admin_menu",
                        "/delete",
                        "/finalize",
                        "/add_faculty",
                        "/change_faculty",
                        "/add_to_realize",
                        "/block_applicant",
                        "/deblock_applicant",
                        "/applicants").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }

    /**
     * Set password encoder
     * @return Bcrypt password encoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
