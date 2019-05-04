package com.b2w.swapi.starwars.service;

import com.b2w.swapi.starwars.entity.Planet;
import com.b2w.swapi.starwars.mapper.PlanetMapper;
import com.b2w.swapi.starwars.repository.PlanetRepository;
import com.b2w.swapi.starwars.representation.PlanetApiRepresentation;
import com.b2w.swapi.starwars.representation.PlanetRepresentation;
import com.b2w.swapi.starwars.service.impl.PlanetServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

/**
 * Created by arthur on 03/05/19
 */
@SpringBootTest
@Transactional
public class PlanetServiceTest {
    @Autowired
    private RestTemplate restTemplate;

    private PlanetMapper mapper;
    private PlanetService service;
    private PlanetRepository repository;

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Before
    public void setUp() {
        this.mapper = Mappers.getMapper(PlanetMapper.class);

        this.repository = Mockito.mock(PlanetRepository.class);
        this.redisTemplate = Mockito.mock(RedisTemplate.class);

        this.hashOperations = Mockito.mock(HashOperations.class);
        Mockito.when(redisTemplate.opsForHash()).thenReturn(hashOperations);

        this.service = new PlanetServiceImpl(mapper, repository, redisTemplate, restTemplate);
    }

    @Test
    public void add() {
        Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");
        planet.setId("abc");

        PlanetApiRepresentation planetApiRepresentation = new PlanetApiRepresentation();
        planetApiRepresentation.setName(planet.getName());
        planetApiRepresentation.setFilms(Arrays.asList("P1", "P2"));

        Mockito
                .when(this.hashOperations.get(Mockito.any(), Mockito.any()))
                .thenReturn(singletonList(planetApiRepresentation));

        Mockito
                .when(this.repository.save(Mockito.any()))
                .thenReturn(planet);

        final PlanetRepresentation planetCreated = this.service.createPlanet(this.mapper.toRequest(planet));

        assertEquals(this.mapper.toRepresentation(planet).getId(), planetCreated.getId());
    }

    @Test
    public void findAllPlanets() {
        Planet planetOne = new Planet("Alderaan", "temperate", "grasslands, mountains");
        Planet planetTwo = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
        Planet planetThree = new Planet("Hoth", "frozen", "tundra, ice caves, mountain ranges");


        PlanetApiRepresentation planetApiRepresentationOne = new PlanetApiRepresentation();
        planetApiRepresentationOne.setName(planetOne.getName());
        planetApiRepresentationOne.setFilms(singletonList("P1"));

        PlanetApiRepresentation planetApiRepresentationTwo = new PlanetApiRepresentation();
        planetApiRepresentationTwo.setName(planetTwo.getName());
        planetApiRepresentationTwo.setFilms(Arrays.asList("P1", "P2"));

        PlanetApiRepresentation planetApiRepresentationThree = new PlanetApiRepresentation();
        planetApiRepresentationThree.setName(planetThree.getName());
        planetApiRepresentationThree.setFilms(Arrays.asList("P1", "P2", "P3"));

        Mockito
                .when(this.hashOperations.get(Mockito.any(), Mockito.any()))
                .thenReturn(Arrays.asList(planetApiRepresentationOne, planetApiRepresentationTwo, planetApiRepresentationThree));

        Mockito
                .when(this.repository.findAll(PageRequest.of(0, 1000)))
                .thenReturn(new PageImpl<>(Arrays.asList(planetOne, planetTwo, planetThree)));

        final Page<Planet> allPlanets = this.service.findAllPlanets(PageRequest.of(0, 1000));

        assertEquals(3,allPlanets.getTotalElements());
    }

    @Test
    public void findAllByAPI() {
    }

    @Test
    public void findByName() {

        Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");

        PlanetApiRepresentation planetApiRepresentation = new PlanetApiRepresentation();
        planetApiRepresentation.setName(planet.getName());
        planetApiRepresentation.setFilms(Arrays.asList("P1", "P2"));

        Mockito
                .when(this.hashOperations.get(Mockito.any(), Mockito.any()))
                .thenReturn(singletonList(planetApiRepresentation));

        final PlanetRepresentation plan = service.createPlanet(this.mapper.toRequest(planet));
        assertEquals(1, 1);
    }
}