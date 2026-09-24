package ms_pedidos_360.bff.client;


import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.graph.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GraphClient {


    private final GraphServiceClient graphServiceClient;


    public GraphClient(
            GraphServiceClient graphServiceClient
    ) {
        this.graphServiceClient =
                graphServiceClient;
    }



    public List<User> obtenerUsuarios() {


        return graphServiceClient
                .users()
                .get()
                .getValue();

    }
}