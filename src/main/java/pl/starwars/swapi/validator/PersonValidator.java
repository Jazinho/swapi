package pl.starwars.swapi.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.starwars.swapi.dao.entity.PersonEntity;

@Component
public class PersonValidator {

    @Value("${validation.person.height.threshold}")
    private Integer personValidationHeightThreshold;

    public boolean validate(PersonEntity person) {
        return person != null && person.getHeight() != null && person.getHeight() < personValidationHeightThreshold;
    }
}
