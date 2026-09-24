package ms_pedidos_360.bff.dto.solicitud;


import ms_pedidos_360.bff.dto.catalog.CategoriaResponse;
import ms_pedidos_360.bff.dto.catalog.PrioridadResponse;

import java.time.LocalDateTime;


public record SolicitudAdminResponse(

        Long id,

        String titulo,

        String descripcion,

        CategoriaResponse categoria,

        PrioridadResponse prioridad,

        UsuarioResponse usuario,

        String estado,

        String operadorAsignado,

        LocalDateTime fechaCreacion,

        LocalDateTime fechaActualizacion

) {}