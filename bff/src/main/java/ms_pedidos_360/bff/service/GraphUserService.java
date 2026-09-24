package ms_pedidos_360.bff.service;


import com.microsoft.graph.models.User;
import com.microsoft.graph.serviceclient.GraphServiceClient;

import lombok.RequiredArgsConstructor;

import ms_pedidos_360.bff.dto.graph.OperatorResponse;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;



@Service
@RequiredArgsConstructor
public class GraphUserService {


    private final GraphServiceClient graphClient;

    @Value("${azure.operators-group-id}")
    private String operatorsGroupId;

    public List<OperatorResponse> obtenerOperadores(){

        var miembros =
                graphClient
                        .groups()
                        .byGroupId(operatorsGroupId)
                        .members()
                        .get()
                        .getValue();

        return miembros.stream()

                .filter(member ->
                        member instanceof User
                )
                .map(member -> {
                    User user =
                            (User) member;
                    return new OperatorResponse(

                            user.getId(),

                            user.getDisplayName(),

                            user.getUserPrincipalName()
                    );
                })
                .toList();
    }


}