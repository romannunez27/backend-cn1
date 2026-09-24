package ms_pedidos_360.bff.service;


import com.microsoft.graph.models.User;

import ms_pedidos_360.bff.client.CatalogClient;
import ms_pedidos_360.bff.client.GraphClient;
import ms_pedidos_360.bff.client.OrdersClient;

import ms_pedidos_360.bff.dto.catalog.CategoriaResponse;
import ms_pedidos_360.bff.dto.catalog.PrioridadResponse;

import ms_pedidos_360.bff.dto.orders.SolicitudResponse;

import ms_pedidos_360.bff.dto.solicitud.SolicitudAdminResponse;
import ms_pedidos_360.bff.dto.solicitud.SolicitudDetalleResponse;
import ms_pedidos_360.bff.dto.solicitud.UsuarioResponse;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SolicitudFacadeService {


    private final OrdersClient ordersClient;

    private final CatalogClient catalogClient;

    private final GraphClient graphClient;



    public SolicitudFacadeService(
            OrdersClient ordersClient,
            CatalogClient catalogClient,
            GraphClient graphClient
    ){

        this.ordersClient = ordersClient;

        this.catalogClient = catalogClient;

        this.graphClient = graphClient;

    }



    /*
        Usuario:
        Obtiene sus propias solicitudes
        enriquecidas con categoría y prioridad
     */
    public List<SolicitudDetalleResponse> obtenerMisSolicitudes(
            String usuario
    ){


        List<SolicitudResponse> solicitudes =
                ordersClient.obtenerMisSolicitudes(usuario);

        System.out.println("===== SOLICITUDES ORDERS =====");

        solicitudes.forEach(s -> {

            System.out.println(
                    "Solicitud ID: "
                            + s.id()
                            + " | UsuarioId: "
                            + s.usuarioSolicitante()
            );

        });

        System.out.println("===== SOLICITUDES ORDERS =====");

        solicitudes.forEach(s -> {

            System.out.println(
                    "Usuario guardado: "
                            + s.usuarioSolicitante()
            );

        });

        List<CategoriaResponse> categorias =
                catalogClient.obtenerCategorias();



        List<PrioridadResponse> prioridades =
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

                            s.usuarioSolicitante(),

                            s.operadorAsignado(),

                            s.fechaCreacion(),

                            s.fechaActualizacion()

                    );


                })
                .toList();

    }



    public List<SolicitudDetalleResponse> obtenerSolicitudesAsignadas(
            String operador
    ){

        List<SolicitudResponse> solicitudes =
                ordersClient.obtenerSolicitudesAsignadas(
                        operador
                );


        List<CategoriaResponse> categorias =
                catalogClient.obtenerCategorias();


        List<PrioridadResponse> prioridades =
                catalogClient.obtenerPrioridades();


        return solicitudes.stream()
                .map(s -> {


                    CategoriaResponse categoria =
                            categorias.stream()
                                    .filter(c ->
                                            c.id()
                                                    .equals(
                                                            s.categoriaId()
                                                    )
                                    )
                                    .findFirst()
                                    .orElse(null);



                    PrioridadResponse prioridad =
                            prioridades.stream()
                                    .filter(p ->
                                            p.id()
                                                    .equals(
                                                            s.prioridadId()
                                                    )
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

                            s.usuarioSolicitante(),

                            s.operadorAsignado(),

                            s.fechaCreacion(),

                            s.fechaActualizacion()

                    );

                })
                .toList();

    }

    /*
        Admin:
        Obtiene todas las solicitudes
        enriquecidas con:
        - categoría
        - prioridad
        - usuario desde Entra ID
     */
    public List<SolicitudAdminResponse> obtenerTodas(){


        System.out.println("===== ENTRE A OBTENER TODAS =====");



        List<SolicitudResponse> solicitudes =
                ordersClient.obtenerTodas();



        System.out.println("===== SOLICITUDES ORDERS ADMIN =====");

        solicitudes.forEach(s -> {

            System.out.println(
                    "ID: "
                            + s.id()
                            + " | UsuarioId: "
                            + s.usuarioSolicitante()
            );

        });



        List<CategoriaResponse> categorias =
                catalogClient.obtenerCategorias();



        List<PrioridadResponse> prioridades =
                catalogClient.obtenerPrioridades();



        List<User> usuarios =
                graphClient.obtenerUsuarios();



        System.out.println("===== USUARIOS GRAPH =====");

        usuarios.forEach(u -> {

            System.out.println(
                    "Graph: "
                            + u.getId()
                            + " | UPN: "
                            + u.getUserPrincipalName()
                            + " | MAIL: "
                            + u.getMail()
                            + " | OTHER MAILS: "
                            + u.getOtherMails()
            );

        });




        return solicitudes.stream()
                .map(s -> {



                    CategoriaResponse categoria =
                            categorias.stream()
                                    .filter(c ->
                                            c.id()
                                                    .equals(
                                                            s.categoriaId()
                                                    )
                                    )
                                    .findFirst()
                                    .orElse(null);




                    PrioridadResponse prioridad =
                            prioridades.stream()
                                    .filter(p ->
                                            p.id()
                                                    .equals(
                                                            s.prioridadId()
                                                    )
                                    )
                                    .findFirst()
                                    .orElse(null);




                    User usuarioEncontrado =
                            usuarios.stream()
                                    .filter(u -> {

                                        String usuarioSolicitud =
                                                s.usuarioSolicitante();


                                        boolean coincideUPN =
                                                u.getUserPrincipalName() != null
                                                        &&
                                                        u.getUserPrincipalName()
                                                                .equalsIgnoreCase(
                                                                        usuarioSolicitud
                                                                );


                                        boolean coincideMail =
                                                u.getMail() != null
                                                        &&
                                                        u.getMail()
                                                                .equalsIgnoreCase(
                                                                        usuarioSolicitud
                                                                );


                                        boolean coincideOtherMail =
                                                u.getOtherMails() != null
                                                        &&
                                                        u.getOtherMails()
                                                                .stream()
                                                                .anyMatch(mail ->
                                                                        mail.equalsIgnoreCase(
                                                                                usuarioSolicitud
                                                                        )
                                                                );


                                        boolean coincideId =
                                                u.getId() != null
                                                        &&
                                                        u.getId()
                                                                .equals(
                                                                        usuarioSolicitud
                                                                );


                                        return coincideUPN
                                                || coincideMail
                                                || coincideOtherMail
                                                || coincideId;

                                    })
                                    .findFirst()
                                    .orElse(null);




                    System.out.println(
                            "MATCH USUARIO SOLICITUD "
                                    + s.id()
                                    + " => "
                                    + (
                                    usuarioEncontrado != null
                                            ?
                                            usuarioEncontrado.getUserPrincipalName()
                                            :
                                            "NO ENCONTRADO"
                            )
                    );




                    UsuarioResponse usuario = null;



                    if(usuarioEncontrado != null){


                        usuario =
                                new UsuarioResponse(

                                        usuarioEncontrado.getId(),

                                        usuarioEncontrado.getDisplayName(),

                                        usuarioEncontrado.getMail() != null
                                                ?
                                                usuarioEncontrado.getMail()
                                                :
                                                usuarioEncontrado.getUserPrincipalName()

                                );

                    }




                    return new SolicitudAdminResponse(

                            s.id(),

                            s.titulo(),

                            s.descripcion(),

                            categoria,

                            prioridad,

                            usuario,

                            s.estado(),

                            s.operadorAsignado(),

                            s.fechaCreacion(),

                            s.fechaActualizacion()

                    );


                })
                .toList();

    }


}