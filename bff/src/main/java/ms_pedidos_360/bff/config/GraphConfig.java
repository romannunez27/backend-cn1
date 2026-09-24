package ms_pedidos_360.bff.config;


import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;

import com.microsoft.graph.serviceclient.GraphServiceClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GraphConfig {


    @Value("${azure.tenant-id}")
    private String tenantId;


    @Value("${azure.client-id}")
    private String clientId;


    @Value("${azure.client-secret}")
    private String clientSecret;



    @Bean
    public GraphServiceClient graphClient(){


        ClientSecretCredential credential =
                new ClientSecretCredentialBuilder()

                        .tenantId(tenantId)

                        .clientId(clientId)

                        .clientSecret(clientSecret)

                        .build();



        return new GraphServiceClient(
                credential,
                new String[]{
                        "https://graph.microsoft.com/.default"
                }
        );

    }

}