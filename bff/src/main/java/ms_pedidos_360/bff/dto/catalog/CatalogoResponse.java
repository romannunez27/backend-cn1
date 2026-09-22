package ms_pedidos_360.bff.dto.catalog;

import java.util.List;

public record CatalogoResponse(
        List<CategoriaResponse> categorias,
        List<PrioridadResponse> prioridades
) {
}