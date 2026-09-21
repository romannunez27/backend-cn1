package ms_pedidos_360.catalog.controller;

import ms_pedidos_360.catalog.dto.CatalogoV2Response;
import ms_pedidos_360.catalog.model.Categoria;
import ms_pedidos_360.catalog.model.Prioridad;
import ms_pedidos_360.catalog.service.CategoriaService;
import ms_pedidos_360.catalog.service.PrioridadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v2/catalogo")
public class CatalogoV2Controller {

    private final CategoriaService categoriaService;
    private final PrioridadService prioridadService;

    public CatalogoV2Controller(
            CategoriaService categoriaService,
            PrioridadService prioridadService) {

        this.categoriaService = categoriaService;
        this.prioridadService = prioridadService;
    }

    @GetMapping
    public ResponseEntity<CatalogoV2Response> obtenerCatalogo() {

        List<Categoria> categorias = categoriaService.listarCategorias();
        List<Prioridad> prioridades = prioridadService.listarPrioridades();

        CatalogoV2Response response = new CatalogoV2Response(
                "v2",
                LocalDateTime.now(),
                categorias.size(),
                prioridades.size(),
                categorias,
                prioridades
        );

        return ResponseEntity.ok(response);
    }
}