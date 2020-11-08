package pl.starwars.swapi.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import pl.starwars.swapi.dao.entity.PersonEntity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonValidatorTest {

    @Autowired
    PersonValidator personValidator;

    @Value("${validation.person.height.threshold}")
    private Integer personValidationHeightThreshold;

    @Test
    public void aboveThresholdTest(){
        Integer personHeight = personValidationHeightThreshold + 1;
        PersonEntity personToValidate = getPersonWithGivenHeight(personHeight);

        boolean result = personValidator.validate(personToValidate);
        assertTrue(result);
    }

    @Test
    public void belowThresholdTest(){
        Integer personHeight = personValidationHeightThreshold - 1;
        PersonEntity personToValidate = getPersonWithGivenHeight(personHeight);

        boolean result = personValidator.validate(personToValidate);
        assertFalse(result);
    }

    @Test
    public void equalThresholdTest(){
        Integer personHeight = personValidationHeightThreshold;
        PersonEntity personToValidate = getPersonWithGivenHeight(personHeight);

        boolean result = personValidator.validate(personToValidate);
        assertTrue(result);
    }

    @Test
    public void nullHeightTest(){
        Integer personHeight = null;
        PersonEntity personToValidate = getPersonWithGivenHeight(personHeight);

        boolean result = personValidator.validate(personToValidate);
        assertFalse(result);
    }

    @Test
    public void nullPersonTest(){
        boolean result = personValidator.validate(null);
        assertFalse(result);
    }

    private PersonEntity getPersonWithGivenHeight(Integer height){
        PersonEntity person = new PersonEntity();
        person.setHeight(height);
        return person;
    }
}
