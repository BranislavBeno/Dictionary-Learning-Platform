package com.dictionary.learning.platform.config;

import com.dictionary.learning.platform.user.AppUserDetailsService;
import com.dictionary.learning.platform.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    public static final String LOGIN_PAGE = "/login";

    /**
     * Configures the security filter chain for the application.
     *
     * @param httpSecurity the HttpSecurity to modify
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while configuring the HttpSecurity
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth.requestMatchers(LOGIN_PAGE, "/error", "/actuator/info")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                // Configure form login
                .formLogin(login ->
                        login.loginPage(LOGIN_PAGE).defaultSuccessUrl("/").permitAll())
                // Configure logout
                .logout(logout -> logout.logoutSuccessUrl(LOGIN_PAGE).deleteCookies("JSESSIONID"))
                // Configure session management
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(1));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(@Autowired UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }
}
