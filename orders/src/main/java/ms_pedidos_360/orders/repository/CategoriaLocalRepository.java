
package ms_pedidos_360.orders.repository;

import ms_pedidos_360.orders.model.CategoriaLocal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaLocalRepository
        extends JpaRepository<CategoriaLocal, Long> {

    Optional<CategoriaLocal> findByCategoriaId(Long categoriaId);

    boolean existsByCategoriaId(Long categoriaId);
}
