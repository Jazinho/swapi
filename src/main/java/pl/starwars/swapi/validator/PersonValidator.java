package pl.starwars.swapi.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.starwars.swapi.dao.entity.PersonEntity;

@Component
public class PersonValidator {

    @Value("${validation.person.height.threshold}")
    private Integer personValidationHeightThreshold;

    /**
     This method returns true for person with valid data who has height equals or higher than given threshold.
     @param person Person to validate
     @return boolean value indicating if person is valid according to the validator
     **/
    public boolean validate(PersonEntity person) {
        return person != null && person.getHeight() != null && person.getHeight() >= personValidationHeightThreshold;
    }
}
