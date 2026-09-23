package ms_pedidos_360.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    public SecurityConfig(
            JwtAuthenticationConverter jwtAuthenticationConverter) {

        this.jwtAuthenticationConverter =
                jwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> auth

                        // Información del usuario autenticado
                        .requestMatchers(
                                HttpMethod.GET,
                                "/v1/auth/me"
                        )
                        .authenticated()


                        // Catálogo para crear solicitudes
                        .requestMatchers(
                                HttpMethod.GET,
                                "/v1/catalogo"
                        )
                        .hasAnyRole(
                                "USER",
                                "OPERATOR",
                                "ADMIN"
                        )


                        // CLIENTE: crear solicitud
                        .requestMatchers(
                                HttpMethod.POST,
                                "/v1/solicitudes"
                        )
                        .hasRole("USER")


                        // CLIENTE: consultar sus propias solicitudes
                        .requestMatchers(
                                HttpMethod.GET,
                                "/v1/solicitudes/mias"
                        )
                        .hasRole("USER")


                        // OPERADOR / ADMIN: vista global
                        .requestMatchers(
                                HttpMethod.GET,
                                "/v1/solicitudes"
                        )
                        .hasAnyRole(
                                "OPERATOR",
                                "ADMIN"
                        )


                        // OPERADOR / ADMIN:
                        // asignación, estados, atención, etc.
                        .requestMatchers(
                                "/v1/solicitudes/**"
                        )
                        .hasAnyRole(
                                "OPERATOR",
                                "ADMIN"
                        )


                        .anyRequest()
                        .authenticated()
                )

                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter
                                )
                        )
                );

        return http.build();
    }
}