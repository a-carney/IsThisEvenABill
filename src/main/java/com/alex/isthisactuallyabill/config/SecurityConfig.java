package com.alex.isthisactuallyabill.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.api-key}")
    private String apiKey;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // In-memory authentication for demonstration. Replace with real authentication in production.
        auth.inMemoryAuthentication()
                .withUser("user")  // Username
                .password("{noop}password") // {noop} indicates no password encoder. Use bcrypt in production.
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity. Enable in production.
                .authorizeRequests()
                .antMatchers("/eob/check", "/tce/estimate", "/codes/lookup").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic(); // Use Basic Authentication
    }
}