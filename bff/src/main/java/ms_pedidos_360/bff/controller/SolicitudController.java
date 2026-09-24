package ms_pedidos_360.bff.controller;

import jakarta.validation.Valid;
import ms_pedidos_360.bff.client.OrdersClient;
import ms_pedidos_360.bff.dto.orders.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/v1/solicitudes")
public class SolicitudController {

    private final OrdersClient ordersClient;

    public SolicitudController(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudResponse crearSolicitud(
            @Valid @RequestBody CrearSolicitudRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String usuarioSolicitante =
                jwt.getClaimAsString("preferred_username");

        return ordersClient.crearSolicitud(
                request,
                usuarioSolicitante
        );
    }
    @GetMapping("/asignadas")
    public List<SolicitudResponse> obtenerAsignadas(
            @AuthenticationPrincipal Jwt jwt
    ){

        String operador =
                jwt.getClaimAsString(
                        "preferred_username"
                );


        return ordersClient.obtenerSolicitudesAsignadas(
                operador
        );

    }

    @GetMapping("/mias")
    public List<SolicitudResponse> obtenerMisSolicitudes(
            @AuthenticationPrincipal Jwt jwt) {

        String usuario =
                jwt.getClaimAsString(
                        "preferred_username"
                );

        return ordersClient.obtenerMisSolicitudes(usuario);
    }

    @GetMapping
    public List<SolicitudResponse> obtenerTodas() {

        return ordersClient.obtenerTodas();
    }

    @PatchMapping("/{id}/estado")
    public SolicitudResponse actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoRequest request) {

        return ordersClient.actualizarEstado(
                id,
                request
        );
    }

    @PatchMapping("/{id}/asignacion")
    public SolicitudResponse asignarOperador(
            @PathVariable Long id,
            @Valid @RequestBody AsignarOperadorRequest request) {

        return ordersClient.asignarOperador(
                id,
                request
        );
    }

    @PatchMapping("/{id}/atencion")
    public SolicitudResponse registrarAtencion(
            @PathVariable Long id,
            @Valid @RequestBody RegistrarAtencionRequest request) {

        return ordersClient.registrarAtencion(
                id,
                request
        );
    }
}