package ms_pedidos_360.catalog.controller;

import ms_pedidos_360.catalog.dto.CatalogoResponse;
import ms_pedidos_360.catalog.service.CategoriaService;
import ms_pedidos_360.catalog.service.PrioridadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/catalogo")
public class CatalogoController {

    private final CategoriaService categoriaService;
    private final PrioridadService prioridadService;

    public CatalogoController(
            CategoriaService categoriaService,
            PrioridadService prioridadService) {

        this.categoriaService = categoriaService;
        this.prioridadService = prioridadService;
    }

    @GetMapping
    public ResponseEntity<CatalogoResponse> obtenerCatalogo() {

        CatalogoResponse catalogo = new CatalogoResponse(
                categoriaService.listarCategorias(),
                prioridadService.listarPrioridades()
        );

        return ResponseEntity.ok(catalogo);
    }
}