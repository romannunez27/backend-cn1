package ms_pedidos_360.bff.dto.orders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CrearSolicitudRequest(

        @NotBlank(message = "El titulo es obligatorio")
        @Size(max = 150, message = "El titulo no puede superar los 150 caracteres")
        String titulo,

        @NotBlank(message = "La descripcion es obligatoria")
        @Size(max = 1000, message = "La descripcion no puede superar los 1000 caracteres")
        String descripcion,

        @NotNull(message = "La categoria es obligatoria")
        Long categoriaId,

        @NotNull(message = "La prioridad es obligatoria")
        Long prioridadId
) {
}