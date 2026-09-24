package ms_pedidos_360.bff.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class GraphTokenService {


    @Value("${azure.tenant-id}")
    private String tenantId;


    @Value("${azure.client-id}")
    private String clientId;


    @Value("${azure.client-secret}")
    private String clientSecret;


    private final RestTemplate restTemplate =
            new RestTemplate();



    public String obtenerTokenGraph() {


        String url =
                "https://login.microsoftonline.com/"
                        + tenantId
                        + "/oauth2/v2.0/token";


        MultiValueMap<String,String> body =
                new LinkedMultiValueMap<>();


        body.add(
                "client_id",
                clientId
        );


        body.add(
                "client_secret",
                clientSecret
        );


        body.add(
                "scope",
                "https://graph.microsoft.com/.default"
        );


        body.add(
                "grant_type",
                "client_credentials"
        );


        HttpHeaders headers =
                new HttpHeaders();


        headers.setContentType(
                MediaType.APPLICATION_FORM_URLENCODED
        );


        HttpEntity<?> request =
                new HttpEntity<>(
                        body,
                        headers
                );


        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        url,
                        request,
                        Map.class
                );


        return response.getBody()
                .get("access_token")
                .toString();
    }
}