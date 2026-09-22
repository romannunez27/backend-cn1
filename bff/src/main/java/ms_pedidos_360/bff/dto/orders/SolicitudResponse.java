package ms_pedidos_360.bff.dto.orders;

import java.time.LocalDateTime;

public record SolicitudResponse(
        Long id,
        String titulo,
        String descripcion,
        Long categoriaId,
        Long prioridadId,
        String estado,
        String usuarioId,
        Long operadorId,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion
) {
}