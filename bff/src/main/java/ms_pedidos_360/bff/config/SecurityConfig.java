package ms_pedidos_360.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/catalogo/categorias", "/v1/catalogo/prioridades")
                        .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/catalogo/categorias/**", "/v1/catalogo/prioridades/**")
                        .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/catalogo/categorias/**", "/v1/catalogo/prioridades/**")
                        .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/solicitudes")
                        .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/v1/solicitudes/*/asignacion")
                        .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/v1/solicitudes/*/atencion")
                        .hasAuthority("ROLE_OPERATOR")
                        .requestMatchers(HttpMethod.PATCH, "/v1/solicitudes/*/estado")
                        .hasAnyAuthority("ROLE_OPERATOR", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/solicitudes/asignadas")
                        .hasAuthority("ROLE_OPERATOR")
                        .requestMatchers(HttpMethod.GET, "/v1/users/operators", "/v1/usuarios")
                        .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/v1/solicitudes")
                        .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/solicitudes/mias")
                        .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = new ArrayList<>(scopeConverter.convert(jwt));
            List<String> roles = jwt.getClaimAsStringList("roles");
            if (roles != null) {
                roles.stream()
                        .filter(r -> r.equals("ROLE_USER") || r.equals("ROLE_OPERATOR") || r.equals("ROLE_ADMIN"))
                        .map(SimpleGrantedAuthority::new)
                        .forEach(authorities::add);
            }
            return authorities;
        });
        return converter;
    }
}
