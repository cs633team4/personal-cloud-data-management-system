package com.cs633.team4.clouddatamanagementsystem.config;

import com.cs633.team4.clouddatamanagementsystem.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Restrict unauthorized users from accessing pages other than the login and signup pages.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // configure an authentication mechanism by allowing AuthenticationProviders to be added easily
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    // configure web based security for specific http requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/signup", "/login", "/css/**", "/js/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout");  // redirect to /login?logout after successful logout

        http.formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true); // redirect to /home after successful login

    }
}



