package ms_pedidos_360.bff.dto.solicitud;


import ms_pedidos_360.bff.dto.catalog.CategoriaResponse;
import ms_pedidos_360.bff.dto.catalog.PrioridadResponse;

import java.time.LocalDateTime;


public record SolicitudDetalleResponse(

        Long id,

        String titulo,

        String descripcion,

        CategoriaResponse categoria,

        PrioridadResponse prioridad,

        String estado,

        String usuarioId,

        Long operadorId,

        LocalDateTime fechaCreacion,

        LocalDateTime fechaActualizacion

) {}