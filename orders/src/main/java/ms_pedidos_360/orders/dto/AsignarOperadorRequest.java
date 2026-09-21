package ms_pedidos_360.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AsignarOperadorRequest(

        @NotBlank(message = "El operador es obligatorio")
        @Size(max = 255, message = "El operador no puede superar los 255 caracteres")
        String operador

) {
}