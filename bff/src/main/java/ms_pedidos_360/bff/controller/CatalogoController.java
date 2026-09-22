package ms_pedidos_360.bff.controller;

import ms_pedidos_360.bff.client.CatalogClient;
import ms_pedidos_360.bff.dto.catalog.CatalogoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}