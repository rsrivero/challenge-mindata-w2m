package com.project.challenge.application.usecases;

import com.project.challenge.application.adapter.SpaceshipCommandService;
import com.project.challenge.application.adapter.SpaceshipQueryService;
import com.project.challenge.application.dto.SpaceshipDTO;
import com.project.challenge.application.exceptions.SpaceshipNotFound;
import com.project.challenge.application.mapper.SpaceshipMapper;
import com.project.challenge.domain.model.Spaceship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateSpaceshipService {

    @Autowired
    private SpaceshipCommandService spaceShipCommandService;

    @Autowired
    private SpaceshipQueryService spaceshipQueryService;

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    /**
     * Modify a spaceship given his ID.
     * Update spaceship name and strength with submitted data
     * Throw an exception if the spaceship is not found
     *
     * @param id
     * @param spaceshipRequest SpaceshipDTORequest
     * @return SpaceshipDTO
     * @throws SpaceshipNotFound
     */
    @CachePut(value = "spaceship", key = "#id")
    @Transactional(propagation = Propagation.REQUIRED)
    public SpaceshipDTO update(Integer id, Spaceship spaceshipRequest) throws SpaceshipNotFound {
        var spaceship = spaceshipQueryService.findSpaceship(id).orElseThrow(SpaceshipNotFound::new);
        var spaceshipUpdated = spaceshipMapper.updateDomain(spaceship, spaceshipRequest);
        spaceshipUpdated.setId(id);
        spaceshipUpdated = spaceShipCommandService.update(spaceshipUpdated);
        return spaceshipMapper.toDTO(spaceshipUpdated);
    }
}
