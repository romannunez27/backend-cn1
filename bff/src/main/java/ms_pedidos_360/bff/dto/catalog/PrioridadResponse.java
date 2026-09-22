package ms_pedidos_360.bff.dto.catalog;

public record PrioridadResponse(
        Long id,
        String nombre,
        String descripcion,
        Boolean activo
) {
}