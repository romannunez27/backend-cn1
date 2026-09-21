package ms_pedidos_360.catalog.repository;

import ms_pedidos_360.catalog.model.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrioridadRepository extends JpaRepository<Prioridad, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    Optional<Prioridad> findByNombreIgnoreCase(String nombre);

    List<Prioridad> findByActivoTrue();
}