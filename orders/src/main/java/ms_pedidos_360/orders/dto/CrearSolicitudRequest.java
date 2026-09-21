package ms_pedidos_360.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CrearSolicitudRequest(

        @NotBlank(message = "El título de la solicitud es obligatorio")
        @Size(max = 150, message = "El título no puede superar los 150 caracteres")
        String titulo,

        @NotBlank(message = "La descripción de la solicitud es obligatoria")
        @Size(max = 2000, message = "La descripción no puede superar los 2000 caracteres")
        String descripcion,

        @NotNull(message = "La categoría es obligatoria")
        @Positive(message = "El identificador de categoría debe ser mayor a 0")
        Long categoriaId,

        @NotNull(message = "La prioridad es obligatoria")
        @Positive(message = "El identificador de prioridad debe ser mayor a 0")
        Long prioridadId,

        @NotBlank(message = "El usuario solicitante es obligatorio")
        @Size(max = 255, message = "El usuario solicitante no puede superar los 255 caracteres")
        String usuarioSolicitante

) {
}