package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AplicationSecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    public AplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    //Create a User in memory
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails Eduardo668 = User.builder()
                .username("Eduardo668")
                .password(passwordEncoder.encode("password"))
                .roles("STUDENT")
                .build();


       UserDetails kalani = User.builder()
               .username("Kalani")
               .password(passwordEncoder.encode("1234"))
               .roles("ADMIN")
               .build();

        return new InMemoryUserDetailsManager(
                Eduardo668,
                kalani
        );
    }
}
