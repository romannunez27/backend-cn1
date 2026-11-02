
package ms_pedidos_360.orders.controller;

import ms_pedidos_360.orders.model.CategoriaLocal;
import ms_pedidos_360.orders.service.CategoriaLocalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaLocalController {

    private final CategoriaLocalService categoriaLocalService;

    public CategoriaLocalController(
            CategoriaLocalService categoriaLocalService) {
        this.categoriaLocalService = categoriaLocalService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaLocal>> listarCategorias() {

        return ResponseEntity.ok(
                categoriaLocalService.listarCategorias()
        );
    }
}
