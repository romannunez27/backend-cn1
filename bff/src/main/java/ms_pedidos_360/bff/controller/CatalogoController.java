package ms_pedidos_360.bff.controller;

import ms_pedidos_360.bff.client.CatalogClient;
import ms_pedidos_360.bff.dto.catalog.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ms_pedidos_360.bff.dto.catalog.CategoriaRequest;
import ms_pedidos_360.bff.dto.catalog.CategoriaResponse;

import ms_pedidos_360.bff.dto.catalog.PrioridadRequest;
import ms_pedidos_360.bff.dto.catalog.PrioridadResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/catalogo")
public class CatalogoController {

    private final CatalogClient catalogClient;

    public CatalogoController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping
    public CatalogoResponse obtenerCatalogo() {
        return catalogClient.obtenerCatalogo();
    }

    @GetMapping("/categorias")
    public List<CategoriaResponse> obtenerCategorias(){
        return catalogClient.obtenerCategorias();
    }

    @PostMapping("/categorias")
    public CategoriaResponse crearCategoria(
            @RequestBody CategoriaRequest request
    ){
        return catalogClient.crearCategoria(request);
    }

    @PutMapping("/categorias/{id}")
    public CategoriaResponse actualizarCategoria(
            @PathVariable Long id,
            @RequestBody CategoriaRequest request
    ){
        return catalogClient.actualizarCategoria(
                id,
                request
        );
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> eliminarCategoria(
            @PathVariable Long id
    ){
        catalogClient.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/prioridades")
    public List<PrioridadResponse> obtenerPrioridades(){
        return catalogClient.obtenerPrioridades();
    }

    @PostMapping("/prioridades")
    public PrioridadResponse crearPrioridad(
            @RequestBody PrioridadRequest request
    ){
        return catalogClient.crearPrioridad(request);
    }

    @PutMapping("/prioridades/{id}")
    public PrioridadResponse actualizarPrioridad(
            @PathVariable Long id,
            @RequestBody PrioridadRequest request
    ){
        return catalogClient.actualizarPrioridad(
                id,
                request
        );
    }
    @DeleteMapping("/prioridades/{id}")
    public ResponseEntity<Void> eliminarPrioridad(
            @PathVariable Long id
    ){
        catalogClient.eliminarPrioridad(id);
        return ResponseEntity.noContent().build();
    }

}