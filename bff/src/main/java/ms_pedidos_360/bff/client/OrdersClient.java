package ms_pedidos_360.bff.client;

import ms_pedidos_360.bff.dto.orders.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Component
public class OrdersClient {

    private final RestClient ordersRestClient;

    public OrdersClient(
            @Qualifier("ordersRestClient")
            RestClient ordersRestClient) {

        this.ordersRestClient = ordersRestClient;
    }

    public SolicitudResponse crearSolicitud(
            CrearSolicitudRequest request,
            String usuarioSolicitante) {

        CrearSolicitudOrdersRequest ordersRequest =
                new CrearSolicitudOrdersRequest(
                        request.titulo(),
                        request.descripcion(),
                        request.categoriaId(),
                        request.prioridadId(),
                        usuarioSolicitante
                );

        return ordersRestClient
                .post()
                .uri("/v1/solicitudes")
                .body(ordersRequest)
                .retrieve()
                .body(SolicitudResponse.class);
    }

    public List<SolicitudResponse> obtenerMisSolicitudes(
            String usuarioSolicitante) {

        return ordersRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/solicitudes/mias")
                        .queryParam(
                                "usuarioSolicitante",
                                usuarioSolicitante
                        )
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<
                        List<SolicitudResponse>>() {});
    }

    public List<SolicitudResponse> obtenerTodas() {

        return ordersRestClient
                .get()
                .uri("/v1/solicitudes")
                .retrieve()
                .body(new ParameterizedTypeReference<
                        List<SolicitudResponse>>() {});
    }

    public SolicitudResponse actualizarEstado(
            Long id,
            ActualizarEstadoRequest request) {

        return ordersRestClient
                .patch()
                .uri("/v1/solicitudes/{id}/estado", id)
                .body(request)
                .retrieve()
                .body(SolicitudResponse.class);
    }

    public SolicitudResponse asignarOperador(
            Long id,
            AsignarOperadorRequest request) {

        return ordersRestClient
                .patch()
                .uri("/v1/solicitudes/{id}/asignacion", id)
                .body(request)
                .retrieve()
                .body(SolicitudResponse.class);
    }

    public SolicitudResponse registrarAtencion(
            Long id,
            RegistrarAtencionRequest request) {

        return ordersRestClient
                .patch()
                .uri("/v1/solicitudes/{id}/atencion", id)
                .body(request)
                .retrieve()
                .body(SolicitudResponse.class);
    }
    public List<SolicitudResponse> obtenerSolicitudesAsignadas(
            String operador
    ){

        return ordersRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/solicitudes/asignadas")
                        .queryParam(
                                "operador",
                                operador
                        )
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<
                        List<SolicitudResponse>>() {});

    }
}