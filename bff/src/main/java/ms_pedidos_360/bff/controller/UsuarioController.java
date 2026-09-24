package ms_pedidos_360.bff.controller;


import ms_pedidos_360.bff.client.GraphClient;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {


    private final GraphClient graphClient;


    public UsuarioController(
            GraphClient graphClient
    ) {
        this.graphClient = graphClient;
    }


    @GetMapping
    public Object usuarios(){

        return graphClient.obtenerUsuarios();

    }
}