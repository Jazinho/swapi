package pl.starwars.swapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.starwars.swapi.dao.repository.PersonRepository;
import pl.starwars.swapi.dto.PersonDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;


    @Test
    public void importAndGetPersonTest(){
        Long personId = 1L;
        Integer mass = 77;
        Integer height = 172;
        String name = "Luke Skywalker";

        clearAndImportIds(personId);

        PersonDto personDto = personService.getPerson(personId);
        assertEquals(name, personDto.getName());
        assertEquals(mass, personDto.getMass());
        assertEquals(height, personDto.getHeight());
    }

    @Test
    public void doubleImportBlockTest(){
        clearAndImportIds(2L, 2L);

        List<PersonDto> personList = personService.getAllPersons();
        assertEquals(1, personList.size());
    }

    @Test
    public void importThreeAndGetListTest(){
        clearAndImportIds(3L, 4L, 5L);

        List<PersonDto> personList = personService.getAllPersons();
        assertEquals(3, personList.size());
    }

    @Test
    public void importNotExistingPersonTest(){
        Long personId = 100L;

        PersonDto personDto = personService.importPersonById(personId);
        assertNull(personDto);
    }

    @Test
    public void importPersonByNullIdTest(){
        PersonDto personDto = personService.importPersonById(null);
        assertNull(personDto);
    }

    @Test
    public void importAndFilterSinglePersonByNameTest(){
        Integer mass = 77;
        Integer height = 172;
        String name = "Luke Skywalker";

        clearAndImportIds(1L);

        List<PersonDto> personDtoList = personService.getPersonsByName("UkE sKy");
        assertEquals(1, personDtoList.size());
        assertEquals(height, personDtoList.get(0).getHeight());
        assertEquals(mass, personDtoList.get(0).getMass());
        assertEquals(name, personDtoList.get(0).getName());
    }

    @Test
    public void importAndFilterSinglePersonByEmptyStringTest(){
        Integer mass = 77;
        Integer height = 172;
        String name = "Luke Skywalker";

        clearAndImportIds(1L);

        List<PersonDto> personDtoList = personService.getPersonsByName("");
        assertEquals(1, personDtoList.size());
        assertEquals(height, personDtoList.get(0).getHeight());
        assertEquals(mass, personDtoList.get(0).getMass());
        assertEquals(name, personDtoList.get(0).getName());
    }

    /**
     * Leia Organa, R2-D2 and R5-D4 matches filter. C-3P0 and Obi-Wan Kenobi does not.
     */
    @Test
    public void importAndFilterMultiplePersonByEmptyStringTest(){
        clearAndImportIds(2L, 3L, 5L, 8L, 10L);

        List<PersonDto> personDtoList = personService.getPersonsByName("r");
        assertEquals(3, personDtoList.size());
    }

    private void clearAndImportIds(Long... ids){
        personRepository.deleteAll();

        for(Long id: ids){
            personService.importPersonById(id);
        }
    }
}
