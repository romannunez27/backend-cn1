package ms_pedidos_360.orders.exception;

public class EstadoSolicitudInvalidoException extends RuntimeException {

    public EstadoSolicitudInvalidoException(String mensaje) {
        super(mensaje);
    }
}