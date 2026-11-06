package ms_pedidos_360.bff.dto.orders;

/** Categoria replicada en la RDS de Solicitudes. */
public record CategoriaLocalResponse(
        Long id,
        Long categoriaId,
        String nombre,
        String eventId,
        String fechaEvento
) {}
