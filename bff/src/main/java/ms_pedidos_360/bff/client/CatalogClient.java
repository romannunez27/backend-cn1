package ms_pedidos_360.bff.client;

import ms_pedidos_360.bff.dto.catalog.CatalogoResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogClient {

    private final RestClient catalogRestClient;

    public CatalogClient(
            @Qualifier("catalogRestClient")
            RestClient catalogRestClient) {

        this.catalogRestClient = catalogRestClient;
    }

    public CatalogoResponse obtenerCatalogo() {

        return catalogRestClient
                .get()
                .uri("/v1/catalogo")
                .retrieve()
                .body(CatalogoResponse.class);
    }
}