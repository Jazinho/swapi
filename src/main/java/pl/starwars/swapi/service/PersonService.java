package pl.starwars.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.starwars.swapi.dao.entity.PersonEntity;
import pl.starwars.swapi.dao.repository.PersonRepository;
import pl.starwars.swapi.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;


    public List<PersonDto> getAllPersons(){
        return personRepository.findAll().stream()
                .map(person -> mapPersonToDto(person))
                .collect(Collectors.toList());
    }

    public PersonDto getPerson(Long id){
        PersonEntity personEntity = personRepository.getOne(id);
        if(personEntity != null){
            return mapPersonToDto(personEntity);
        } else {
            return null;
        }
    }

    public List<PersonDto> getPersonsByName(String name){
        return personRepository.findByNameContainingIgnoreCase(name).stream()
                .map(person -> mapPersonToDto(person))
                .collect(Collectors.toList());
    }

    private PersonDto mapPersonToDto(PersonEntity personEntity){
        PersonDto personDto = new PersonDto();
        personDto.setName(personEntity.getName());
        personDto.setHeight(personEntity.getHeight());
        personDto.setMass(personEntity.getMass());
        return personDto;
    }
}
