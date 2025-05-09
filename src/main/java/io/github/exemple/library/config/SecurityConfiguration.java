package io.github.exemple.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(configurer ->{
                    configurer.loginPage("/login").permitAll();})
                //.formLogin(Customizer.withDefaults())

                .authorizeHttpRequests(autorize ->{
                    autorize.requestMatchers("/login").permitAll();
                    /*
                    autorize.requestMatchers(HttpMethod.POST, "/autores/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.DELETE, "/autores/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.PUT, "/autores/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.GET, "/autores/**").hasAnyRole("USER", "ADMIN");
                     */
                    autorize.requestMatchers("/autores/**").hasRole("ADMIN");
                    autorize.requestMatchers("/livros/**").hasAnyRole("USER", "ADMIN");

                    autorize.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails user1 = User.builder()
                .username("usuario")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
