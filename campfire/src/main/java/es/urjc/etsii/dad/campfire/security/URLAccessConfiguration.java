package es.urjc.etsii.dad.campfire.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class URLAccessConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();

        // Private pages
        http.authorizeRequests().anyRequest().authenticated();

        // Login Form
        http.formLogin().loginPage("/");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/home");
        http.formLogin().failureUrl("/");

        // Logout
        http.logout().logoutUrl("/log-out");
        http.logout().logoutSuccessUrl("/");

        http.csrf().disable();
    }

}
