package com.limpiezait.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableMethodSecurity //Habilita la seguridad a nivel de método, lo que permite usar anotaciones como @PreAuthorize en los controladores para restringir el acceso a ciertos métodos según los roles de usuario.
@Configuration
public class SecurityConfig {
	
	
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/public/**").permitAll()
	                .requestMatchers("/api/admin/**").hasRole("ADMIN")
	                .anyRequest().authenticated()
	            ) 
	            .httpBasic(basic -> {});

	        return http.build();
	    }
	    
	    @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User.withDefaultPasswordEncoder()
	            .username("user")
	            .password("password")
	            .roles("USER")
	            .build();

	        UserDetails admin = User.withDefaultPasswordEncoder()
	            .username("admin")
	            .password("admin123")
	            .roles("USER", "ADMIN")
	            .build();

	        return new InMemoryUserDetailsManager(user, admin);
	    }

}
