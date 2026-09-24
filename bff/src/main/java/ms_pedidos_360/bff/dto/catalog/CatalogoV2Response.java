package ms_pedidos_360.bff.dto.catalog;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class CatalogoV2Response {


    private String version;


    private LocalDateTime generadoEn;


    private Integer totalCategorias;


    private Integer totalPrioridades;


    private List<CategoriaResponse> categorias;


    private List<PrioridadResponse> prioridades;

}