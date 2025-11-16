package syksy25.card;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())  // IMPORTANT → allows POST/PUT/DELETE from Postman

            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/login", "/css/**", "/js/**", "/error").permitAll()

                // Allow REST API to be tested from Postman
                .requestMatchers("/api/**").permitAll()  

                // Everything else requires login
                .anyRequest().authenticated()
            )

            // Enable form login
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/addresslist", true)
                .permitAll()
            )

            // Enable Basic Auth for Postman
            .httpBasic(httpBasic -> {})

            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}