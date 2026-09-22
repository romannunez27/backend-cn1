package ms_pedidos_360.bff.controller;

import jakarta.validation.Valid;
import ms_pedidos_360.bff.client.OrdersClient;
import ms_pedidos_360.bff.dto.orders.CrearSolicitudRequest;
import ms_pedidos_360.bff.dto.orders.SolicitudResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/solicitudes")
public class SolicitudController {

    private final OrdersClient ordersClient;

    public SolicitudController(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudResponse crearSolicitud(
            @Valid @RequestBody CrearSolicitudRequest request) {

        return ordersClient.crearSolicitud(request);
    }
}