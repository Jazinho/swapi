package pl.starwars.swapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.starwars.swapi.dto.PersonDto;
import pl.starwars.swapi.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(value = "persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "")
    public List<PersonDto> getPersons(){
        return personService.getAllPersons();
    }

    @GetMapping(value = "/{id}")
    public PersonDto getPerson(@PathVariable(name = "id") Long id){
        return personService.getPerson(id);
    }

    @GetMapping(value = "/name/{filter}")
    public List<PersonDto> getPersonsByName(@PathVariable(value = "filter") String filter){
        return personService.getPersonsByName(filter);
    }

    @PutMapping(value = "/import")
    public PersonDto importPersonById(@RequestParam(value = "id") Long id){
        return personService.importPersonById(id);
    }

}
