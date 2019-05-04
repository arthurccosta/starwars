package com.b2w.swapi.starwars.request.validation;

import com.b2w.swapi.starwars.request.PlanetRequest;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by arthur on 04/05/19
 */
public class PlanetValidator implements ConstraintValidator<PlanetValid, PlanetRequest> {

    @Override
    public void initialize(PlanetValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(PlanetRequest request, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation();

        if (request.getName() == null) {
            context.buildConstraintViolationWithTemplate("Name can not be null")
                    .addPropertyNode("name")
                    .addConstraintViolation();
            return false;
        }
        if (request.getTerrain() == null) {
            context.buildConstraintViolationWithTemplate("Terrain can not be null")
                    .addPropertyNode("terrain")
                    .addConstraintViolation();
            return false;
        }
        if (request.getClimate() == null) {
            context.buildConstraintViolationWithTemplate("Climate can not be null")
                    .addPropertyNode("climate")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
