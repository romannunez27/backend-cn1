package ms_pedidos_360.catalog.dto;

import ms_pedidos_360.catalog.model.Categoria;
import ms_pedidos_360.catalog.model.Prioridad;

import java.time.LocalDateTime;
import java.util.List;

public record CatalogoV2Response(
        String version,
        LocalDateTime generadoEn,
        int totalCategorias,
        int totalPrioridades,
        List<Categoria> categorias,
        List<Prioridad> prioridades
) {
}