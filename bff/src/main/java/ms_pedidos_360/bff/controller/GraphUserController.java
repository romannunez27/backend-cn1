package ms_pedidos_360.bff.controller;

import lombok.RequiredArgsConstructor;

import ms_pedidos_360.bff.dto.graph.OperatorResponse;
import ms_pedidos_360.bff.service.GraphUserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class GraphUserController {

    private final GraphUserService graphUserService;
    @GetMapping("/operators")
    public List<OperatorResponse> obtenerOperadores(){
        return graphUserService.obtenerOperadores();
    }
}