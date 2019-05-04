package com.b2w.swapi.starwars.service;

import com.b2w.swapi.starwars.entity.Planet;
import com.b2w.swapi.starwars.exception.PlanetNotFound;
import com.b2w.swapi.starwars.representation.PlanetApiRepresentation;
import com.b2w.swapi.starwars.representation.PlanetRepresentation;
import com.b2w.swapi.starwars.request.PlanetRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by arthur on 01/05/19
 */
public interface PlanetService {

    PlanetRepresentation createPlanet(final PlanetRequest request);

    Page<Planet> findAllPlanets(final Pageable pageable);

    List<PlanetApiRepresentation> findAllByAPI();

    PlanetRepresentation findByName(String name) throws PlanetNotFound;

    PlanetRepresentation findById(String id) throws PlanetNotFound;

    PlanetRepresentation delete(String id) throws PlanetNotFound;
}
