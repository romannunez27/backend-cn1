package ms_pedidos_360.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrarAtencionRequest(

        @NotBlank(message = "El detalle de atención es obligatorio")
        @Size(max = 2000, message = "El detalle de atención no puede superar los 2000 caracteres")
        String detalle

) {
}