package ms_pedidos_360.bff.dto.orders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CrearSolicitudRequest(

        @NotBlank(message = "El titulo es obligatorio")
        @Size(max = 150)
        String titulo,

        @NotBlank(message = "La descripcion es obligatoria")
        @Size(max = 2000)
        String descripcion,

        @NotNull(message = "La categoria es obligatoria")
        @Positive
        Long categoriaId,

        @NotNull(message = "La prioridad es obligatoria")
        @Positive
        Long prioridadId
) {
}