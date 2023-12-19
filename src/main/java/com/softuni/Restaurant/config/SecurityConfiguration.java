package com.softuni.Restaurant.config;

import com.softuni.Restaurant.model.enums.UserRoles;
import com.softuni.Restaurant.repository.UserRepository;
import com.softuni.Restaurant.service.UserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                // Define which urls are visible by which users
                authorizeRequests -> authorizeRequests
                        // All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/","/index","/about", "/login", "/register", "/menu","/resturant1","/resturant2", "/users/login-error").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/product-add","/order-panel", "/product-panel", "/users-panel").hasRole(UserRoles.ADMIN.name())
                        .requestMatchers("/cart", "/order-add", "/profile", "/shoppingCart").hasRole(UserRoles.USER.name())
                        // all other requests are authenticated.
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> formLogin
                        // redirect here when we access something which is not allowed.
                        // also this is the page where we perform login.
                        .loginPage("/login")
                        // The names of the input fields (in our case in auth-login.html)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/menu")
                        .failureForwardUrl("/users/login-error")
        ).logout(
                logout -> logout
                        // the URL where we should POST something in order to perform the logout
                        .logoutUrl("/logout")
                        // where to go when logged out?
                        .logoutSuccessUrl("/index")
                        // invalidate the HTTP session
                        .invalidateHttpSession(true)
        ).build();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates the restaurant users and roles
        // to representation which spring security understands.
        return new UserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
