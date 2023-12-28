    package com.healthIQ.healthIQ.Configurations;

    import lombok.RequiredArgsConstructor;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
    import org.springframework.web.filter.CorsFilter;


    import java.util.Arrays;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(Customizer.withDefaults())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/v1/auth/**","/registrationConfirm","forgotPasswordRecovery").permitAll()
                            .anyRequest()
                            .authenticated())
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            ;

            return http.build();
        }
        @Bean
        public CorsFilter corsFilter(){
              final UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
              final CorsConfiguration config =new CorsConfiguration();
              config.setAllowCredentials(true);
              config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
              config.setAllowedHeaders(Arrays.asList("*"));
              config.setAllowedMethods(Arrays.asList("*"));
              source.registerCorsConfiguration("/**",config);
              return new CorsFilter(source);
        }
    }