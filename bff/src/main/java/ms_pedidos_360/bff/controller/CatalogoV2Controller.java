package ms_pedidos_360.bff.controller;


import ms_pedidos_360.bff.client.CatalogClient;
import ms_pedidos_360.bff.dto.catalog.CatalogoV2Response;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v2/catalogo")
public class CatalogoV2Controller {

    private final CatalogClient catalogClient;

    public CatalogoV2Controller(
            CatalogClient catalogClient
    ){
        this.catalogClient = catalogClient;
    }
    @GetMapping
    public CatalogoV2Response obtenerCatalogoV2(){
        return catalogClient.obtenerCatalogoV2();
    }
}