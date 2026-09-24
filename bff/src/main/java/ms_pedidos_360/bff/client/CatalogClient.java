package ms_pedidos_360.bff.client;

import ms_pedidos_360.bff.dto.catalog.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;

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
    public CatalogoV2Response obtenerCatalogoV2() {


        return catalogRestClient
                .get()
                .uri("/v2/catalogo")
                .retrieve()
                .body(CatalogoV2Response.class);


    }

    public List<CategoriaResponse> obtenerCategorias(){

        return catalogRestClient
                .get()
                .uri("/v1/catalogo/categorias")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

    }



    public CategoriaResponse crearCategoria(
            CategoriaRequest request
    ){

        return catalogRestClient
                .post()
                .uri("/v1/catalogo/categorias")
                .body(request)
                .retrieve()
                .body(CategoriaResponse.class);

    }



    public CategoriaResponse actualizarCategoria(
            Long id,
            CategoriaRequest request
    ){

        return catalogRestClient
                .put()
                .uri("/v1/catalogo/categorias/{id}", id)
                .body(request)
                .retrieve()
                .body(CategoriaResponse.class);

    }



    public void eliminarCategoria(Long id){

        catalogRestClient
                .delete()
                .uri("/v1/catalogo/categorias/{id}", id)
                .retrieve()
                .toBodilessEntity();

    }
    public List<PrioridadResponse> obtenerPrioridades(){

        return catalogRestClient
                .get()
                .uri("/v1/catalogo/prioridades")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

    }



    public PrioridadResponse crearPrioridad(
            PrioridadRequest request
    ){

        return catalogRestClient
                .post()
                .uri("/v1/catalogo/prioridades")
                .body(request)
                .retrieve()
                .body(PrioridadResponse.class);

    }



    public PrioridadResponse actualizarPrioridad(
            Long id,
            PrioridadRequest request
    ){

        return catalogRestClient
                .put()
                .uri("/v1/catalogo/prioridades/{id}", id)
                .body(request)
                .retrieve()
                .body(PrioridadResponse.class);

    }



    public void eliminarPrioridad(Long id){

        catalogRestClient
                .delete()
                .uri("/v1/catalogo/prioridades/{id}", id)
                .retrieve()
                .toBodilessEntity();

    }
}