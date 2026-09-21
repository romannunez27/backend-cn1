package ms_pedidos_360.catalog.service;

import ms_pedidos_360.catalog.exception.PrioridadDuplicadaException;
import ms_pedidos_360.catalog.exception.PrioridadNoEncontradaException;
import ms_pedidos_360.catalog.model.Prioridad;
import ms_pedidos_360.catalog.repository.PrioridadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrioridadService {

    private final PrioridadRepository prioridadRepository;

    public PrioridadService(PrioridadRepository prioridadRepository) {
        this.prioridadRepository = prioridadRepository;
    }

    public List<Prioridad> listarPrioridades() {
        return prioridadRepository.findByActivoTrue();
    }

    public List<Prioridad> listarTodas() {
        return prioridadRepository.findAll();
    }

    public Prioridad buscarPorId(Long id) {
        return prioridadRepository.findById(id)
                .orElseThrow(() ->
                        new PrioridadNoEncontradaException(
                                "Prioridad no encontrada con id: " + id
                        )
                );
    }

    public Prioridad crear(Prioridad prioridad) {

        if (prioridadRepository.existsByNombreIgnoreCase(prioridad.getNombre())) {
            throw new PrioridadDuplicadaException(
                    "Ya existe una prioridad con el nombre: " + prioridad.getNombre()
            );
        }

        prioridad.setId(null);
        prioridad.setActivo(true);

        return prioridadRepository.save(prioridad);
    }

    public Prioridad actualizar(Long id, Prioridad datosPrioridad) {

        Prioridad prioridad = buscarPorId(id);

        var prioridadMismoNombre =
                prioridadRepository.findByNombreIgnoreCase(datosPrioridad.getNombre());

        if (prioridadMismoNombre.isPresent()
                && !prioridadMismoNombre.get().getId().equals(id)) {

            throw new PrioridadDuplicadaException(
                    "Ya existe una prioridad con el nombre: "
                            + datosPrioridad.getNombre()
            );
        }

        prioridad.setNombre(datosPrioridad.getNombre());
        prioridad.setDescripcion(datosPrioridad.getDescripcion());

        if (datosPrioridad.getActivo() != null) {
            prioridad.setActivo(datosPrioridad.getActivo());
        }

        return prioridadRepository.save(prioridad);
    }

    public void desactivar(Long id) {

        Prioridad prioridad = buscarPorId(id);

        prioridad.setActivo(false);

        prioridadRepository.save(prioridad);
    }
}