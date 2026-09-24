package ms_pedidos_360.bff.service;


import com.microsoft.graph.serviceclient.GraphServiceClient;

import lombok.RequiredArgsConstructor;

import ms_pedidos_360.bff.dto.graph.OperatorResponse;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class GraphUserService {


    private final GraphServiceClient graphClient;



    public List<OperatorResponse> obtenerOperadores(){


        /*
         *
         * Aquí irá la consulta a Microsoft Graph
         *
         */


        return List.of();


    }


}