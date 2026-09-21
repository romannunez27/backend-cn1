package ms_pedidos_360.orders.repository;

import ms_pedidos_360.orders.model.EstadoSolicitud;
import ms_pedidos_360.orders.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    List<Solicitud> findByUsuarioSolicitanteOrderByFechaCreacionDesc(
            String usuarioSolicitante
    );

    List<Solicitud> findAllByOrderByFechaCreacionDesc();

    List<Solicitud> findByOperadorAsignadoOrderByFechaCreacionDesc(
            String operadorAsignado
    );

    List<Solicitud> findByOperadorAsignadoIsNullOrderByFechaCreacionDesc();

    List<Solicitud> findByOperadorAsignadoIsNullAndEstadoOrderByFechaCreacionDesc(
            EstadoSolicitud estado
    );
}