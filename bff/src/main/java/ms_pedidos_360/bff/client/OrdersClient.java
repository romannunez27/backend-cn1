package ms_pedidos_360.bff.client;

import ms_pedidos_360.bff.dto.orders.CrearSolicitudRequest;
import ms_pedidos_360.bff.dto.orders.SolicitudResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OrdersClient {

    private final RestClient ordersRestClient;

    public OrdersClient(
            @Qualifier("ordersRestClient")
            RestClient ordersRestClient) {

        this.ordersRestClient = ordersRestClient;
    }

    public SolicitudResponse crearSolicitud(
            CrearSolicitudRequest request) {

        return ordersRestClient
                .post()
                .uri("/v1/solicitudes")
                .body(request)
                .retrieve()
                .body(SolicitudResponse.class);
    }
}