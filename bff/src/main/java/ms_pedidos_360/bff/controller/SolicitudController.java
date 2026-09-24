package ms_pedidos_360.bff.controller;


import jakarta.validation.Valid;

import ms_pedidos_360.bff.client.OrdersClient;

import ms_pedidos_360.bff.dto.orders.*;

import ms_pedidos_360.bff.dto.solicitud.SolicitudDetalleResponse;

import ms_pedidos_360.bff.service.SolicitudFacadeService;

import org.springframework.http.HttpStatus;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.oauth2.jwt.Jwt;

import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/v1/solicitudes")
public class SolicitudController {

    private final OrdersClient ordersClient;

    private final SolicitudFacadeService solicitudFacadeService;

    public SolicitudController(
            OrdersClient ordersClient,
            SolicitudFacadeService solicitudFacadeService
    ) {

        this.ordersClient = ordersClient;
        this.solicitudFacadeService =
                solicitudFacadeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudResponse crearSolicitud(
            @Valid @RequestBody CrearSolicitudRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        String usuarioSolicitante =
                jwt.getClaimAsString(
                        "preferred_username"
                );
        return ordersClient.crearSolicitud(
                request,
                usuarioSolicitante
        );
    }
    @GetMapping("/mias")
    public List<SolicitudDetalleResponse> obtenerMisSolicitudes(
            @AuthenticationPrincipal Jwt jwt) {
        String usuario =
                jwt.getClaimAsString(
                        "preferred_username"
                );
        return solicitudFacadeService
                .obtenerMisSolicitudes(usuario);

    }
    @GetMapping("/asignadas")
    public List<SolicitudResponse> obtenerAsignadas(
            @AuthenticationPrincipal Jwt jwt
    ) {

        System.out.println(jwt.getClaims());

        String operador =
                jwt.getClaimAsString("oid");


        System.out.println(
                "OID BUSCADO: " + operador
        );


        return ordersClient.obtenerSolicitudesAsignadas(
                operador
        );
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