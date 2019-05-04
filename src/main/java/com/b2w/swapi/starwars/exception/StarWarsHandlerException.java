package com.b2w.swapi.starwars.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by arthur on 01/05/19
 */
@ControllerAdvice
public class StarWarsHandlerException {

    private final Logger logger = getLogger(this.getClass());

    @ExceptionHandler(PlanetNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Object> notFoundException(PlanetNotFound ex) {

        logger.error("NotFoundException: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(NOT_FOUND);
    }
}
