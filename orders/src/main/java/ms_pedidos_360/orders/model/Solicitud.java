package ms_pedidos_360.orders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título de la solicitud es obligatorio")
    @Size(
            max = 150,
            message = "El título no puede superar los 150 caracteres"
    )
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "La descripción de la solicitud es obligatoria")
    @Size(
            max = 2000,
            message = "La descripción no puede superar los 2000 caracteres"
    )
    @Column(nullable = false, length = 2000)
    private String descripcion;

    @NotNull(message = "La categoría es obligatoria")
    @Positive(message = "El identificador de categoría debe ser mayor a 0")
    @Column(nullable = false)
    private Long categoriaId;

    @NotNull(message = "La prioridad es obligatoria")
    @Positive(message = "El identificador de prioridad debe ser mayor a 0")
    @Column(nullable = false)
    private Long prioridadId;

    @NotBlank(message = "El usuario solicitante es obligatorio")
    @Size(
            max = 255,
            message = "El usuario solicitante no puede superar los 255 caracteres"
    )
    @Column(nullable = false, length = 255)
    private String usuarioSolicitante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoSolicitud estado = EstadoSolicitud.CREADA;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @Size(
            max = 255,
            message = "El operador asignado no puede superar los 255 caracteres"
    )
    @Column(length = 255)
    private String operadorAsignado;

    @Size(
            max = 2000,
            message = "El detalle de atención no puede superar los 2000 caracteres"
    )
    @Column(length = 2000)
    private String detalleAtencion;

    @PrePersist
    public void prePersist() {

        LocalDateTime ahora = LocalDateTime.now();

        estado = EstadoSolicitud.CREADA;
        fechaCreacion = ahora;
        fechaActualizacion = ahora;
    }

    @PreUpdate
    public void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}