
package ms_pedidos_360.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "categorias_locales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador original de MS Catalog
    @Column(nullable = false, unique = true)
    private Long categoriaId;

    @Column(nullable = false, length = 150)
    private String nombre;

    // Identificador del evento recibido
    @Column(length = 100)
    private String eventId;

    // Fecha original de creación del evento
    private LocalDateTime fechaEvento;
}
