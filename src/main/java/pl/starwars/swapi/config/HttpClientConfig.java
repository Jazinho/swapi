package pl.starwars.swapi.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HttpClientConfig {

    @Bean
    HttpClient httpClient(){
        return HttpClients.createDefault();
    }
}
