package br.edu.ifpr.projeto_hospital.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.ifpr.projeto_hospital.service.CustomUserDetailsService;
import br.edu.ifpr.projeto_hospital.service.CustomAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

private final CustomAuthenticationSuccessHandler successHandler;

public WebSecurityConfig(CustomUserDetailsService userDetailsService,
        CustomAuthenticationSuccessHandler successHandler) {
    this.userDetailsService = userDetailsService;
    this.successHandler = successHandler;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/doctor-page/**").hasRole("Doctor")
                    .requestMatchers("/pharmaceutical-page/**").hasRole("Pharmaceutical")
                    .anyRequest().authenticated())
            .formLogin((form) -> form
                    .loginPage("/login")
                    .successHandler(successHandler)
                    .permitAll())
            .logout((logout) -> logout.permitAll());

return http.build();
}

@Bean
public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
}

}
