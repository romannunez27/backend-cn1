package ms_pedidos_360.bff.controller;

import ms_pedidos_360.bff.client.OrdersClient;
import ms_pedidos_360.bff.dto.catalog.CategoriaResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/v1/solicitudes/categorias")
public class CategoriaSolicitudController {
    private final OrdersClient ordersClient;

    public CategoriaSolicitudController(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;
    }

    @GetMapping
    public List<CategoriaResponse> categoriasDisponibles() {
        // 'id' es el ID original de Catálogo; no el id interno de la copia.
        return ordersClient.obtenerCategoriasLocales().stream()
                .map(c -> new CategoriaResponse(c.categoriaId(), c.nombre(), null, true))
                .toList();
    }
}
