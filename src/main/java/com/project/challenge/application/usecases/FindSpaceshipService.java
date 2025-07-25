package com.project.challenge.application.usecases;

import com.project.challenge.application.adapter.SpaceshipQueryService;
import com.project.challenge.application.dto.SpaceshipDTO;
import com.project.challenge.application.exceptions.SpaceshipNotFound;
import com.project.challenge.application.mapper.SpaceshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindSpaceshipService {

    @Autowired
    private SpaceshipQueryService spaceshipQueryService;

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    /**
     * Spaceship Search by ID Throw an exception if not found.
     * The query result is cached.
     * Throw an exception if the spaceship is not found
     *
     * @param id Integer
     * @return SpaceshipDTO
     * @throws SpaceshipNotFound
     */
    @Cacheable( value = "spaceship")
    public SpaceshipDTO findSpaceship(Integer id) throws SpaceshipNotFound {
        var spaceship = spaceshipQueryService.findSpaceship(id).orElseThrow(SpaceshipNotFound::new);
        return spaceshipMapper.toDTO(spaceship);
    }

    /**
     * Spaceship Search with Specification
     *
     * @param pageable
     * @return Page<SpaceshipDTO>
     */
    public Page<SpaceshipDTO> findAll(Pageable pageable) {
        var spaceships = spaceshipQueryService.findAllPaged(pageable);
        return  spaceships.map(spaceshipMapper::toDTO);
    }

    /**
     * Spaceship Search with Specification
     *
     * @param name
     * @return List<SpaceshipDTO>
     */
    public List<SpaceshipDTO> searchByName(String name, Pageable pageable) {
        var spaceships = spaceshipQueryService.searchByName(name, pageable);
        return spaceships.stream().map(spaceshipMapper::toDTO).toList();
    }
}
