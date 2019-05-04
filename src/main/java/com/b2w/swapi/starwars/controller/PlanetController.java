package com.b2w.swapi.starwars.controller;

import com.b2w.swapi.starwars.entity.Planet;
import com.b2w.swapi.starwars.exception.PlanetNotFound;
import com.b2w.swapi.starwars.representation.PlanetApiRepresentation;
import com.b2w.swapi.starwars.representation.PlanetRepresentation;
import com.b2w.swapi.starwars.request.PlanetRequest;
import com.b2w.swapi.starwars.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by arthur on 01/05/19
 */
@RestController
@RequestMapping("planet")
public class PlanetController {
    private PlanetService service;

    @Autowired
    public PlanetController(PlanetService planetService) {
        this.service = planetService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public PlanetRepresentation newPlanet(@Valid @RequestBody PlanetRequest request) {
        return this.service.createPlanet(request);
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public PlanetRepresentation findById(@PathVariable String id) throws PlanetNotFound {
        return this.service.findById(id);
    }

    @GetMapping()
    @ResponseStatus(OK)
    public Page<Planet> findAll(final Pageable pageable) {
        return this.service.findAllPlanets(pageable);
    }

    @GetMapping("/findAll/swapi")
    @ResponseStatus(OK)
    public List<PlanetApiRepresentation> findAllByAPI() {
        return this.service.findAllByAPI();
    }

    @GetMapping("findByName")
    @ResponseStatus(OK)
    public PlanetRepresentation findByName(@Param("name") String name) throws PlanetNotFound {
        return this.service.findByName(name);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePlanet(@PathVariable String id) throws PlanetNotFound {
        this.service.delete(id);
    }
}
