package pl.starwars.swapi.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.starwars.swapi.client.model.SwPerson;
import pl.starwars.swapi.client.model.SwPersonImpl;

import java.io.IOException;

@Service
public class SwClientImpl implements SwClient {

    @Autowired
    HttpClient httpClient;

    private ObjectMapper mapper = new ObjectMapper();

    @Value("${swapi.person.base.url}")
    private String swapiPersonBaseUri;

    @Override
    public SwPerson getPerson(Long id) {
        HttpGet httpGet = new HttpGet(swapiPersonBaseUri + "/" + id);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                return mapper.readValue(httpResponse.getEntity().getContent(), SwPersonImpl.class);
            }
        } catch (IOException e){
            System.out.println("IOException " + e.getMessage());
        }

        return null;
    }
}
