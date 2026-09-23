package ms_pedidos_360.bff.dto.orders;

import jakarta.validation.constraints.NotBlank;

public record RegistrarAtencionRequest(

        @NotBlank(message = "El detalle es obligatorio")
        String detalle

) {
}