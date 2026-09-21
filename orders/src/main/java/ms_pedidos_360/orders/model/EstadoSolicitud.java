package ms_pedidos_360.orders.model;

public enum EstadoSolicitud {

    CREADA,
    ASIGNADA,
    EN_PROCESO,
    RESUELTA,
    CERRADA,
    CANCELADA;

    public boolean puedeTransicionarA(EstadoSolicitud nuevoEstado) {

        return switch (this) {

            case CREADA ->
                    nuevoEstado == ASIGNADA
                            || nuevoEstado == CANCELADA;

            case ASIGNADA ->
                    nuevoEstado == EN_PROCESO
                            || nuevoEstado == CANCELADA;

            case EN_PROCESO ->
                    nuevoEstado == RESUELTA
                            || nuevoEstado == CANCELADA;

            case RESUELTA ->
                    nuevoEstado == CERRADA;

            case CERRADA, CANCELADA ->
                    false;
        };
    }
}