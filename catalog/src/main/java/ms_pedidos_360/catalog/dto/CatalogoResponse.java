package ms_pedidos_360.catalog.dto;

import ms_pedidos_360.catalog.model.Categoria;
import ms_pedidos_360.catalog.model.Prioridad;

import java.util.List;

public record CatalogoResponse(
        List<Categoria> categorias,
        List<Prioridad> prioridades
) {
}