package com.b2w.swapi.starwars.repository;

import com.b2w.swapi.starwars.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by arthur on 01/05/19
 */
public interface PlanetRepository extends MongoRepository<Planet, String> {
    Optional<Planet> findByName(String name);
}
