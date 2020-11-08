package pl.starwars.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.starwars.swapi.client.SwClient;
import pl.starwars.swapi.client.model.SwPerson;
import pl.starwars.swapi.dao.entity.PersonEntity;
import pl.starwars.swapi.dao.repository.PersonRepository;
import pl.starwars.swapi.dto.PersonDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SwClient swClient;


    public List<PersonDto> getAllPersons(){
        return personRepository.findAll().stream()
                .map(person -> mapPersonEntityToDto(person))
                .collect(Collectors.toList());
    }

    public PersonDto getPerson(Long id){
        if(id != null) {
            Optional<PersonEntity> personEntity = personRepository.findById(id);
            if (personEntity.isPresent()) {
                return mapPersonEntityToDto(personEntity.get());
            }
        }

        return null;
    }

    public List<PersonDto> getPersonsByName(String name){
        if(name != null) {
            return personRepository.findByNameContainingIgnoreCase(name).stream()
                    .map(person -> mapPersonEntityToDto(person))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public PersonDto importPersonById(Long id){
        if(id != null) {
            SwPerson importedSwPerson = swClient.getPerson(id);
            if (importedSwPerson != null) {
                PersonEntity personToSave = personRepository.findByName(importedSwPerson.getName());
                if (personToSave != null) {
                    personToSave.setName(importedSwPerson.getName());
                    personToSave.setMass(importedSwPerson.getMass());
                    personToSave.setHeight(importedSwPerson.getHeight());
                } else {
                    personToSave = mapSwPersonToPersonEntity(importedSwPerson);
                }
                PersonEntity savedPerson = personRepository.save(personToSave);
                return mapPersonEntityToDto(savedPerson);
            }
        }

        return null;
    }

    private PersonDto mapPersonEntityToDto(PersonEntity personEntity){
        PersonDto personDto = new PersonDto();
        personDto.setName(personEntity.getName());
        personDto.setHeight(personEntity.getHeight());
        personDto.setMass(personEntity.getMass());
        return personDto;
    }

    private PersonEntity mapSwPersonToPersonEntity(SwPerson swPerson){
        PersonEntity entity = new PersonEntity();
        entity.setName(swPerson.getName());
        entity.setHeight(swPerson.getHeight());
        entity.setMass(swPerson.getMass());
        return entity;
    }
}
