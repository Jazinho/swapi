package pl.starwars.swapi.client;

import pl.starwars.swapi.client.model.SwPerson;

public interface SwClient {

    SwPerson getPerson(Long id);
}
