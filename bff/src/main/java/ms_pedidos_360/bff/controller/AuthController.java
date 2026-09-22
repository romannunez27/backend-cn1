package ms_pedidos_360.bff.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @GetMapping("/me")
    public Map<String, Object> obtenerUsuario(
            @AuthenticationPrincipal Jwt jwt) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("name", jwt.getClaimAsString("name"));
        response.put(
                "username",
                jwt.getClaimAsString("preferred_username")
        );
        response.put("oid", jwt.getClaimAsString("oid"));
        response.put("aud", jwt.getAudience());
        response.put("scp", jwt.getClaimAsString("scp"));
        response.put("roles", jwt.getClaimAsStringList("roles"));

        return response;
    }
}