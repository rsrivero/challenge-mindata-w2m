package com.project.challenge.infrastructure.rest.controller;

import com.project.challenge.application.exceptions.SpaceshipNotFound;
import com.project.challenge.application.mapper.SpaceshipMapper;
import com.project.challenge.application.usecases.DeleteSpaceshipService;
import com.project.challenge.application.usecases.FindSpaceshipService;
import com.project.challenge.application.usecases.SaveSpaceshipService;
import com.project.challenge.application.usecases.UpdateSpaceshipService;
import com.project.challenge.infrastructure.rest.request.SpaceshipDTORequest;
import com.project.challenge.infrastructure.rest.response.SpaceshipDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/spaceship")
public class SpaceshipController {

    @Autowired
    private SaveSpaceshipService saveSpaceshipService;

    @Autowired
    private UpdateSpaceshipService spaceshipUpdaterService;

    @Autowired
    private FindSpaceshipService spaceshipFinderService;

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    @Autowired
    private DeleteSpaceshipService spaceshipEliminatorService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SpaceshipDTOResponse create(@RequestBody SpaceshipDTORequest req){
        var spaceship = saveSpaceshipService.save(req);
        return spaceshipMapper.toResponse(spaceship);
    }

    @GetMapping("/{id}")
    public SpaceshipDTOResponse find(@PathVariable Integer id) throws SpaceshipNotFound {
        var spaceship = spaceshipFinderService.findSpaceship(id);
        return spaceshipMapper.toResponse(spaceship);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws SpaceshipNotFound {
        spaceshipEliminatorService.delete(id);
    }

    @PutMapping(value = "/{id}",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SpaceshipDTOResponse update(@RequestBody SpaceshipDTORequest req, @PathVariable Integer id) throws SpaceshipNotFound {

        var spaceship = spaceshipUpdaterService.update(id, req);

        return spaceshipMapper.toResponse(spaceship);
    }

    @GetMapping
    public Page<SpaceshipDTOResponse> findAll(Pageable pageable,
                                              @RequestParam(required = false) String name) {
        var spaceships = spaceshipFinderService.findAll(pageable, name);

        return spaceships.map(spaceshipMapper::toResponse);
    }

}
