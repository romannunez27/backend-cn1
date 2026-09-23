package ms_pedidos_360.bff.dto.orders;

import jakarta.validation.constraints.NotBlank;

public record ActualizarEstadoRequest(

        @NotBlank(message = "El estado es obligatorio")
        String estado

) {
}