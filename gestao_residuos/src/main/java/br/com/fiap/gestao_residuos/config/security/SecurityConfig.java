package br.com.fiap.gestao_residuos.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // AUTH
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // POST - SOMENTE ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/descartes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/residuos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/local-coleta/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/coletas/**").hasRole("ADMIN")

                        // PUT - SOMENTE ADMIN
                        .requestMatchers(HttpMethod.PUT, "/api/descartes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/residuos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/local-coleta/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/coletas/**").hasRole("ADMIN")

                        // DELETE - SOMENTE ADMIN
                        .requestMatchers(HttpMethod.DELETE, "/api/descartes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/residuos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/local-coleta/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/coletas/**").hasRole("ADMIN")

                        //USUÁRIOS
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasRole("ADMIN")

                        // GET - USER E ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/**").authenticated()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
