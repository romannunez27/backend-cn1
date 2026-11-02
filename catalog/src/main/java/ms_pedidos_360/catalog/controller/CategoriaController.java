
package ms_pedidos_360.catalog.controller;

import ms_pedidos_360.catalog.dto.CategoriaRequest;
import ms_pedidos_360.catalog.model.Categoria;
import ms_pedidos_360.catalog.publisher.CategoriaPublisher;
import ms_pedidos_360.catalog.service.CategoriaService;

import org.springframework.amqp.AmqpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaPublisher categoriaPublisher;

    public CategoriaController(
            CategoriaService categoriaService,
            CategoriaPublisher categoriaPublisher
    ) {
        this.categoriaService = categoriaService;
        this.categoriaPublisher = categoriaPublisher;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(
            @RequestParam(defaultValue = "false")
            boolean incluirInactivos) {

        if (incluirInactivos) {
            return ResponseEntity.ok(categoriaService.listarTodas());
        }

        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(
            @Valid @RequestBody CategoriaRequest request) {

        Categoria categoriaCreada = categoriaService.crear(request);

        try {
            categoriaPublisher.publicarCategoria(
                    categoriaCreada.getId(),
                    categoriaCreada.getNombre()
            );

        } catch (AmqpException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "La categoría se guardó en RDS, pero no se pudo "
                            + "confirmar su publicación en RabbitMQ",
                    e
            );
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Categoria categoria) {

        return ResponseEntity.ok(
                categoriaService.actualizar(id, categoria)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {

        categoriaService.desactivar(id);

        return ResponseEntity.noContent().build();
    }
}
