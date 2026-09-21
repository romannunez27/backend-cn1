package ms_pedidos_360.orders.service;

import ms_pedidos_360.orders.exception.EstadoSolicitudInvalidoException;
import ms_pedidos_360.orders.exception.SolicitudNoEncontradaException;
import ms_pedidos_360.orders.exception.SolicitudYaAsignadaException;
import ms_pedidos_360.orders.model.EstadoSolicitud;
import ms_pedidos_360.orders.model.Solicitud;
import ms_pedidos_360.orders.repository.SolicitudRepository;
import org.springframework.stereotype.Service;
import ms_pedidos_360.orders.dto.CrearSolicitudRequest;

import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }


    public List<Solicitud> listarTodas() {
        return solicitudRepository.findAllByOrderByFechaCreacionDesc();
    }

    public List<Solicitud> listarMias(String usuarioSolicitante) {
        return solicitudRepository
                .findByUsuarioSolicitanteOrderByFechaCreacionDesc(
                        usuarioSolicitante
                );
    }

    public List<Solicitud> listarAsignadas(String operador) {
        return solicitudRepository
                .findByOperadorAsignadoOrderByFechaCreacionDesc(
                        operador
                );
    }

    public List<Solicitud> listarDisponibles() {
        return solicitudRepository
                .findByOperadorAsignadoIsNullOrderByFechaCreacionDesc();
    }

    public Solicitud buscarPorId(Long id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() ->
                        new SolicitudNoEncontradaException(
                                "Solicitud no encontrada con id: " + id
                        )
                );
    }


    public Solicitud crear(CrearSolicitudRequest request) {

        Solicitud solicitud = new Solicitud();

        solicitud.setTitulo(request.titulo());
        solicitud.setDescripcion(request.descripcion());
        solicitud.setCategoriaId(request.categoriaId());
        solicitud.setPrioridadId(request.prioridadId());
        solicitud.setUsuarioSolicitante(request.usuarioSolicitante());

        solicitud.setEstado(EstadoSolicitud.CREADA);
        solicitud.setOperadorAsignado(null);
        solicitud.setDetalleAtencion(null);

        return solicitudRepository.save(solicitud);
    }


    public Solicitud actualizarEstado(
            Long id,
            EstadoSolicitud nuevoEstado) {

        Solicitud solicitud = buscarPorId(id);

        EstadoSolicitud estadoActual = solicitud.getEstado();

        if (!estadoActual.puedeTransicionarA(nuevoEstado)) {

            throw new EstadoSolicitudInvalidoException(
                    "No se permite cambiar el estado de "
                            + estadoActual
                            + " a "
                            + nuevoEstado
            );
        }

        solicitud.setEstado(nuevoEstado);

        return solicitudRepository.save(solicitud);
    }


    public Solicitud asignarOperador(
            Long id,
            String operador) {

        Solicitud solicitud = buscarPorId(id);

        if (solicitud.getOperadorAsignado() != null
                && !solicitud.getOperadorAsignado().isBlank()) {

            throw new SolicitudYaAsignadaException(
                    "La solicitud ya se encuentra asignada al operador: "
                            + solicitud.getOperadorAsignado()
            );
        }

        if (!solicitud.getEstado()
                .puedeTransicionarA(EstadoSolicitud.ASIGNADA)) {

            throw new EstadoSolicitudInvalidoException(
                    "La solicitud no puede ser asignada "
                            + "porque se encuentra en estado "
                            + solicitud.getEstado()
            );
        }

        solicitud.setOperadorAsignado(operador);
        solicitud.setEstado(EstadoSolicitud.ASIGNADA);

        return solicitudRepository.save(solicitud);
    }


    public Solicitud registrarAtencion(
            Long id,
            String detalleAtencion) {

        Solicitud solicitud = buscarPorId(id);

        solicitud.setDetalleAtencion(detalleAtencion);

        return solicitudRepository.save(solicitud);
    }
}