package ms_pedidos_360.catalog.exception;

public class CategoriaNoEncontradaException extends RuntimeException {

    public CategoriaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}