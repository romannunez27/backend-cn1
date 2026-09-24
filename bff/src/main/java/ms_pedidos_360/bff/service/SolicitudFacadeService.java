package ms_pedidos_360.bff.service;


import ms_pedidos_360.bff.client.CatalogClient;
import ms_pedidos_360.bff.client.OrdersClient;
import ms_pedidos_360.bff.dto.catalog.CategoriaResponse;
import ms_pedidos_360.bff.dto.catalog.PrioridadResponse;
import ms_pedidos_360.bff.dto.orders.SolicitudResponse;
import ms_pedidos_360.bff.dto.solicitud.*;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SolicitudFacadeService {


    private final OrdersClient ordersClient;

    private final CatalogClient catalogClient;



    public SolicitudFacadeService(
            OrdersClient ordersClient,
            CatalogClient catalogClient
    ){
        this.ordersClient = ordersClient;
        this.catalogClient = catalogClient;
    }



    public List<SolicitudDetalleResponse> obtenerMisSolicitudes(
            String usuario
    ){


        List<SolicitudResponse> solicitudes =
                ordersClient.obtenerMisSolicitudes(usuario);



        var categorias =
                catalogClient.obtenerCategorias();


        var prioridades =
                catalogClient.obtenerPrioridades();



        return solicitudes.stream()
                .map(s -> {


                    CategoriaResponse categoria =
                            categorias.stream()
                                    .filter(c ->
                                            c.id()
                                                    .equals(s.categoriaId())
                                    )
                                    .findFirst()
                                    .orElse(null);



                    PrioridadResponse prioridad =
                            prioridades.stream()
                                    .filter(p ->
                                            p.id()
                                                    .equals(s.prioridadId())
                                    )
                                    .findFirst()
                                    .orElse(null);



                    return new SolicitudDetalleResponse(

                            s.id(),
                            s.titulo(),
                            s.descripcion(),
                            categoria,
                            prioridad,
                            s.estado(),
                            s.usuarioId(),
                            s.operadorId(),
                            s.fechaCreacion(),
                            s.fechaActualizacion()

                    );

                })
                .toList();

    }

}