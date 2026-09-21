package ms_pedidos_360.orders.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import ms_pedidos_360.orders.dto.ActualizarEstadoRequest;
import ms_pedidos_360.orders.dto.AsignarOperadorRequest;
import ms_pedidos_360.orders.dto.CrearSolicitudRequest;
import ms_pedidos_360.orders.dto.RegistrarAtencionRequest;
import ms_pedidos_360.orders.model.Solicitud;
import ms_pedidos_360.orders.service.SolicitudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/solicitudes")
@Validated
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<Solicitud> crear(
            @Valid @RequestBody CrearSolicitudRequest request) {

        Solicitud solicitud = solicitudService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitud);
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> listarTodas() {
        return ResponseEntity.ok(
                solicitudService.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                solicitudService.buscarPorId(id)
        );
    }

    @GetMapping("/mias")
    public ResponseEntity<List<Solicitud>> listarMias(
            @RequestParam
            @NotBlank(message = "El usuario solicitante es obligatorio")
            String usuarioSolicitante) {

        return ResponseEntity.ok(
                solicitudService.listarMias(usuarioSolicitante)
        );
    }

    @GetMapping("/asignadas")
    public ResponseEntity<List<Solicitud>> listarAsignadas(
            @RequestParam
            @NotBlank(message = "El operador es obligatorio")
            String operador) {

        return ResponseEntity.ok(
                solicitudService.listarAsignadas(operador)
        );
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Solicitud>> listarDisponibles() {

        return ResponseEntity.ok(
                solicitudService.listarDisponibles()
        );
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Solicitud> actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoRequest request) {

        return ResponseEntity.ok(
                solicitudService.actualizarEstado(
                        id,
                        request.estado()
                )
        );
    }

    @PatchMapping("/{id}/asignacion")
    public ResponseEntity<Solicitud> asignarOperador(
            @PathVariable Long id,
            @Valid @RequestBody AsignarOperadorRequest request) {

        return ResponseEntity.ok(
                solicitudService.asignarOperador(
                        id,
                        request.operador()
                )
        );
    }

    @PatchMapping("/{id}/atencion")
    public ResponseEntity<Solicitud> registrarAtencion(
            @PathVariable Long id,
            @Valid @RequestBody RegistrarAtencionRequest request) {

        return ResponseEntity.ok(
                solicitudService.registrarAtencion(
                        id,
                        request.detalle()
                )
        );
    }
}