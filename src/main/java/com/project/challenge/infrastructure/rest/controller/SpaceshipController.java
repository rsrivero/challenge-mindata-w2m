package com.project.challenge.infrastructure.rest.controller;

import com.project.challenge.application.exceptions.SpaceshipNotFound;
import com.project.challenge.application.mapper.SpaceshipMapper;
import com.project.challenge.application.usecases.DeleteSpaceshipService;
import com.project.challenge.application.usecases.FindSpaceshipService;
import com.project.challenge.application.usecases.SaveSpaceshipService;
import com.project.challenge.application.usecases.UpdateSpaceshipService;
import com.project.challenge.infrastructure.mapper.SpaceshipRequestMapper;
import com.project.challenge.infrastructure.mapper.SpaceshipResponseMapper;
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

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;

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
    private SpaceshipRequestMapper requestMapper;

    @Autowired
    private SpaceshipResponseMapper responseMapper;

    @Autowired
    private DeleteSpaceshipService spaceshipEliminatorService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SpaceshipDTOResponse create(@RequestBody SpaceshipDTORequest req){
        var spaceship = saveSpaceshipService.save(requestMapper.toDomain(req));
        return responseMapper.toResponse(spaceship);
    }

    @GetMapping("/{id}")
    public SpaceshipDTOResponse find(@PathVariable Integer id) throws SpaceshipNotFound {
        var spaceship = spaceshipFinderService.findSpaceship(id);
        return responseMapper.toResponse(spaceship);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws SpaceshipNotFound {
        spaceshipEliminatorService.delete(id);
    }

    @PutMapping(value = "/{id}",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SpaceshipDTOResponse update(@RequestBody SpaceshipDTORequest req, @PathVariable Integer id) throws SpaceshipNotFound {

        var spaceship = spaceshipUpdaterService.update(id, requestMapper.toDomain(req));

        return responseMapper.toResponse(spaceship);
    }

    @GetMapping
    @Parameter(
            name = "pageable",
            description = "Parámetros de paginación",
            examples = @ExampleObject(
                    name = "Ejemplo de paginación",
                    value = "{\"page\":0,\"size\":10,\"sort\":\"id,asc\"}"
            )
    )
    public Page<SpaceshipDTOResponse> findAll(Pageable pageable) {
        var spaceships = spaceshipFinderService.findAll(pageable);

        return spaceships.map(responseMapper::toResponse);
    }

    @GetMapping("/searchByName")
    @Parameter(
            name = "pageable",
            description = "Parámetros de paginación y ordenamiento",
            examples = @ExampleObject(
                    name = "Ejemplo de paginación",
                    value = "{\"page\":0,\"size\":10,\"sort\":\"name,asc\"}"
            )
    )
    public List<SpaceshipDTOResponse> searchByName(@RequestParam String name, Pageable pageable) {
        var spaceships = spaceshipFinderService.searchByName(name,pageable);
        return spaceships.stream().map(responseMapper::toResponse).toList();
    }
}
