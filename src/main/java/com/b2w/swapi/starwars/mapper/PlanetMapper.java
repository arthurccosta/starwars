package com.b2w.swapi.starwars.mapper;

import com.b2w.swapi.starwars.entity.Planet;
import com.b2w.swapi.starwars.representation.PlanetRepresentation;
import com.b2w.swapi.starwars.request.PlanetRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by arthur on 01/05/19
 */
@Mapper(componentModel = "spring")
public interface PlanetMapper {
    PlanetRepresentation toRepresentation(Planet planet);
    Planet fromRepresentation(PlanetRepresentation planetRepresentation);

    @Mapping(target = "qtdFilms", constant = "1")
    Planet fromRequest(PlanetRequest planetRequest);
    PlanetRequest toRequest(Planet planet);
}
