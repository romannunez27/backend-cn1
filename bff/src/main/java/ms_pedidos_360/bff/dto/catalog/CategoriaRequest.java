package ms_pedidos_360.bff.dto.catalog;

public record CategoriaRequest(

        String nombre,

        String descripcion,

        Boolean activo

) {
}