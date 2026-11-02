
package ms_pedidos_360.orders.service;

import ms_pedidos_360.orders.dto.CategoriaMensaje;
import ms_pedidos_360.orders.model.CategoriaLocal;
import ms_pedidos_360.orders.repository.CategoriaLocalRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaLocalService {

    private final CategoriaLocalRepository categoriaLocalRepository;

    public CategoriaLocalService(
            CategoriaLocalRepository categoriaLocalRepository) {
        this.categoriaLocalRepository = categoriaLocalRepository;
    }

    @Transactional
    public void procesarCategoria(CategoriaMensaje mensaje) {

        // Validar que el mensaje exista
        if (mensaje == null) {
            throw new IllegalArgumentException(
                    "El mensaje recibido no puede ser nulo"
            );
        }

        // Validar tipo de evento
        if (!"CATEGORIA_CREADA".equals(mensaje.getTipoEvento())) {
            throw new IllegalArgumentException(
                    "Tipo de evento no válido: " + mensaje.getTipoEvento()
            );
        }

        // Validar identificador original
        if (mensaje.getCategoriaId() == null ||
                mensaje.getCategoriaId() <= 0) {
            throw new IllegalArgumentException(
                    "El ID de la categoría no es válido"
            );
        }

        // Validar nombre
        if (mensaje.getNombre() == null ||
                mensaje.getNombre().isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la categoría es obligatorio"
            );
        }

        // Evitar registros duplicados
        if (categoriaLocalRepository.existsByCategoriaId(
                mensaje.getCategoriaId())) {
            return;
        }

        // Convertir DTO a entidad
        CategoriaLocal categoria = new CategoriaLocal();

        categoria.setCategoriaId(mensaje.getCategoriaId());
        categoria.setNombre(mensaje.getNombre());
        categoria.setEventId(mensaje.getEventId());
        categoria.setFechaEvento(mensaje.getFechaEvento());

        // Persistir categoría en RDS Solicitudes
        categoriaLocalRepository.save(categoria);
    }


    @Transactional(readOnly = true)
    public java.util.List<CategoriaLocal> listarCategorias() {
        return categoriaLocalRepository.findAll();
    }

}
