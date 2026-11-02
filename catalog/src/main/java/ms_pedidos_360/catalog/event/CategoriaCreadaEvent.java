package ms_pedidos_360.catalog.event;

public record CategoriaCreadaEvent(
        String eventId,
        String tipoEvento,
        String fechaEvento,
        Long categoriaId,
        String nombre
) {
}