package ms_pedidos_360.orders.dto;

import jakarta.validation.constraints.NotNull;
import ms_pedidos_360.orders.model.EstadoSolicitud;

public record ActualizarEstadoRequest(

        @NotNull(message = "El nuevo estado es obligatorio")
        EstadoSolicitud estado

) {
}