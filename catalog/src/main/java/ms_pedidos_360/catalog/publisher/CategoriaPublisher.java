
package ms_pedidos_360.catalog.publisher;

import ms_pedidos_360.catalog.event.CategoriaCreadaEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class CategoriaPublisher {

    private static final Logger log =
            LoggerFactory.getLogger(CategoriaPublisher.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    public void publicarCategoria(Long categoriaId, String nombre) {

        String eventId = UUID.randomUUID().toString();

        CategoriaCreadaEvent evento = new CategoriaCreadaEvent(
                eventId,
                "CATEGORIA_CREADA",
                Instant.now().toString(),
                categoriaId,
                nombre
        );

        CorrelationData correlationData =
                new CorrelationData(eventId);

        log.info(
                "Publicando categoría: categoriaId={}, eventId={}",
                categoriaId, eventId
        );

        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                evento,
                message -> {
                    message.getMessageProperties()
                            .setDeliveryMode(MessageDeliveryMode.PERSISTENT);

                    message.getMessageProperties()
                            .setMessageId(eventId);

                    return message;
                },
                correlationData
        );

        try {
            CorrelationData.Confirm confirm =
                    correlationData.getFuture()
                            .get(10, TimeUnit.SECONDS);

            if (!confirm.ack()) {
                throw new AmqpException(
                        "RabbitMQ no confirmó la publicación: "
                                + confirm.reason()
                );
            }

            if (correlationData.getReturned() != null) {
                throw new AmqpException(
                        "RabbitMQ no pudo dirigir el evento a una cola"
                );
            }

            log.info(
                    "Evento publicado correctamente: categoriaId={}, eventId={}",
                    categoriaId, eventId
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new AmqpException(
                    "Se interrumpió la confirmación de RabbitMQ", e
            );

        } catch (ExecutionException | TimeoutException e) {
            throw new AmqpException(
                    "No se pudo confirmar la publicación en RabbitMQ", e
            );
        }
    }
}
