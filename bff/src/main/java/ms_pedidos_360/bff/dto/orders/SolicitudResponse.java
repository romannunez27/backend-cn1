package ms_pedidos_360.bff.dto.orders;

import java.time.LocalDateTime;

public record SolicitudResponse(

        Long id,

        String titulo,

        String descripcion,

        Long categoriaId,

        Long prioridadId,

        String estado,

        String usuarioSolicitante,

        String operadorAsignado,

        LocalDateTime fechaCreacion,

        LocalDateTime fechaActualizacion

) {
}