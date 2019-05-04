package com.b2w.swapi.starwars.request.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by arthur on 04/05/19
 */
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {PlanetValidator.class})
@Documented
public @interface PlanetValid {
    String message() default "Invalid Planet Request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}