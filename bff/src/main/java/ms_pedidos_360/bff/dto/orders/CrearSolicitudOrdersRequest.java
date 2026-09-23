package ms_pedidos_360.bff.dto.orders;

public record CrearSolicitudOrdersRequest(
        String titulo,
        String descripcion,
        Long categoriaId,
        Long prioridadId,
        String usuarioSolicitante
) {
}