package ms_pedidos_360.catalog.controller;

import jakarta.validation.Valid;
import ms_pedidos_360.catalog.model.Prioridad;
import ms_pedidos_360.catalog.service.PrioridadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/prioridades")
public class PrioridadController {

    private final PrioridadService prioridadService;

    public PrioridadController(PrioridadService prioridadService) {
        this.prioridadService = prioridadService;
    }

    @GetMapping
    public ResponseEntity<List<Prioridad>> listarPrioridades(
            @RequestParam(defaultValue = "false") boolean incluirInactivos) {

        if (incluirInactivos) {
            return ResponseEntity.ok(prioridadService.listarTodas());
        }

        return ResponseEntity.ok(prioridadService.listarPrioridades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prioridad> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prioridadService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Prioridad> crear(@Valid @RequestBody Prioridad prioridad) {

        Prioridad prioridadCreada = prioridadService.crear(prioridad);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(prioridadCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prioridad> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Prioridad prioridad) {

        return ResponseEntity.ok(
                prioridadService.actualizar(id, prioridad)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {

        prioridadService.desactivar(id);

        return ResponseEntity.noContent().build();
    }
}