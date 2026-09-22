package ms_pedidos_360.bff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean("catalogRestClient")
    public RestClient catalogRestClient(
            RestClient.Builder builder,
            @Value("${services.catalog.url}") String catalogUrl) {

        return builder
                .baseUrl(catalogUrl)
                .build();
    }

    @Bean("ordersRestClient")
    public RestClient ordersRestClient(
            RestClient.Builder builder,
            @Value("${services.orders.url}") String ordersUrl) {

        return builder
                .baseUrl(ordersUrl)
                .build();
    }
}