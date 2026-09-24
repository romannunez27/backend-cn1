package ms_pedidos_360.bff.config;


import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.kiota.authentication.AzureIdentityAuthenticationProvider;
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
    public GraphServiceClient graphServiceClient() {


        ClientSecretCredential credential =
                new ClientSecretCredentialBuilder()
                        .tenantId(tenantId)
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .build();



        AzureIdentityAuthenticationProvider authProvider =
                new AzureIdentityAuthenticationProvider(
                        credential,
                        new String[]{
                                "graph.microsoft.com"
                        },
                        new String[]{
                                "https://graph.microsoft.com/.default"
                        }
                );



        return new GraphServiceClient(
                authProvider
        );
    }
}