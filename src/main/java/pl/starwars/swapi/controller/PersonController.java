package pl.starwars.swapi.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.starwars.swapi.dto.PersonDto;
import pl.starwars.swapi.service.PersonService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @ApiOperation(value = "Get all persons")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<PersonDto> getPersons(){
        return personService.getAllPersons();
    }


    @ApiOperation(value = "Get person by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Person's id.")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PersonDto getPerson(@PathVariable(name = "id") Long id, HttpServletResponse response){
        PersonDto personDto = personService.getPerson(id);
        if(personDto == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return personDto;
    }


    @ApiOperation(value = "Get persons by name")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filter", value = "Case insensitive Person's name filter. Could be whole name or just a part of it.")
    })
    @GetMapping(value = "/name/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<PersonDto> getPersonsByName(@PathVariable(value = "filter") String filter){
        return personService.getPersonsByName(filter);
    }


    @ApiOperation(value = "Import and persist person from SW API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SW Person's id")
    })
    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PersonDto importPersonById(@RequestParam(value = "id") Long id, HttpServletResponse response){
        PersonDto personDto = personService.importPersonById(id);
        if(personDto == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return personDto;
    }

}
