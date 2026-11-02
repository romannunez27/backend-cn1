
package ms_pedidos_360.orders.listener;

import ms_pedidos_360.orders.dto.CategoriaMensaje;
import ms_pedidos_360.orders.service.CategoriaLocalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CategoriaListener {

    private static final Logger logger =
            LoggerFactory.getLogger(CategoriaListener.class);

    private final CategoriaLocalService categoriaLocalService;

    public CategoriaListener(
            CategoriaLocalService categoriaLocalService) {
        this.categoriaLocalService = categoriaLocalService;
    }

    @RabbitListener(
            queues = "${app.rabbitmq.queue:solicitudes.catalogo.queue}"
    )
    public void consumirCategoria(CategoriaMensaje mensaje) {

        logger.info(
                "Evento recibido desde RabbitMQ. EventId: {}, CategoriaId: {}",
                mensaje.getEventId(),
                mensaje.getCategoriaId()
        );

        categoriaLocalService.procesarCategoria(mensaje);

        logger.info(
                "Categoría procesada correctamente. ID: {}, Nombre: {}",
                mensaje.getCategoriaId(),
                mensaje.getNombre()
        );
    }
}
