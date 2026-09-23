package ms_pedidos_360.bff.dto.orders;

import jakarta.validation.constraints.NotBlank;

public record AsignarOperadorRequest(

        @NotBlank(message = "El operador es obligatorio")
        String operador

) {
}