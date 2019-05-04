package com.b2w.swapi.starwars.service.impl;

import com.b2w.swapi.starwars.entity.Planet;
import com.b2w.swapi.starwars.exception.PlanetNotFound;
import com.b2w.swapi.starwars.mapper.PlanetMapper;
import com.b2w.swapi.starwars.repository.PlanetRepository;

import com.b2w.swapi.starwars.representation.PlanetApiRepresentation;
import com.b2w.swapi.starwars.representation.PlanetApiRootRepresentation;
import com.b2w.swapi.starwars.representation.PlanetRepresentation;
import com.b2w.swapi.starwars.request.PlanetRequest;
import com.b2w.swapi.starwars.service.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by arthur on 01/05/19
 */
@Service
public class PlanetServiceImpl implements PlanetService {
    private static final String REDIS_KEY = "redis-planet-cache";
    private static final String URL_SWAPI = "https://swapi.co/api/planets/";

    private final PlanetMapper mapper;
    private final PlanetRepository repository;
    private final RestTemplate restTemplate;

    private CacheImpl<List<PlanetApiRepresentation>> cache;

    @Autowired
    public PlanetServiceImpl(PlanetMapper mapper,
                             PlanetRepository repository,
                             RedisTemplate<String, Object> redisTemplate,
                             RestTemplate restTemplate) {
        this.mapper = mapper;
        this.repository = repository;
        this.restTemplate = restTemplate;

        this.cache = new CacheImpl(REDIS_KEY, redisTemplate.opsForHash());
    }

    @Override
    public PlanetRepresentation createPlanet(PlanetRequest request) {
        this.cache.findCache("api-cache").orElseGet(() -> this.cache.saveCache("api-cache", this.fetchApi()));

        Planet planet = this.mapper.fromRequest(request);
        Integer planetQtd = this.getPlanetQtd(request.getName());

        planet.setQtdFilms(planetQtd);
        final Planet planetCreated = repository.save(planet);

        return this.mapper.toRepresentation(planetCreated);
    }


    @Override
    public Page<Planet> findAllPlanets(final Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<PlanetApiRepresentation> findAllByAPI() {
        return this.cache.findCache("api-cache").orElseGet(() -> this.cache.saveCache("api-cache", this.fetchApi()));
    }

    @Override
    public PlanetRepresentation findByName(String name) throws PlanetNotFound {
        final Planet planet = this.repository
                .findByName(name)
                .orElseThrow(() -> new PlanetNotFound("Planet Not Found"));

        return this.mapper.toRepresentation(planet);
    }

    @Override
    public PlanetRepresentation findById(String id) throws PlanetNotFound {
        final Planet planet = this.findByIdOrThrowNotFound(id);
        return this.mapper.toRepresentation(planet);
    }

    @Override
    public PlanetRepresentation delete(String id) throws PlanetNotFound {
        final Planet planet = this.findByIdOrThrowNotFound(id);
        this.repository.delete(planet);

        return this.mapper.toRepresentation(planet);
    }

    private Planet findByIdOrThrowNotFound(String id) throws PlanetNotFound {
        return this.repository.findById(id).orElseThrow(() -> new PlanetNotFound("Planet Not Found"));
    }

    //UTILS
    private List<PlanetApiRepresentation> fetchApi() {
        HttpEntity<String> entity = getHeaders();
        final ResponseEntity<PlanetApiRootRepresentation> planetsRoot = restTemplate.exchange(URL_SWAPI,
                                                                                              HttpMethod.GET,
                                                                                              entity,
                                                                                              PlanetApiRootRepresentation.class);

        return Objects.requireNonNull(planetsRoot.getBody()).getResults();
    }
    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<String>("parameters", headers);
    }


    private Integer getPlanetQtd(String planetName) {
        List<PlanetApiRepresentation> planetList = this.findAllByAPI();
        Optional<PlanetApiRepresentation> planetOptional = planetList.stream()
                .filter(planet -> planet.getName().equals(planetName)).findAny();

        return planetOptional.map(PlanetApiRepresentation::getFilms).map(List::size).orElse(0);
    }
}
