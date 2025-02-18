package com.project.challenge.application.usecases;

import com.project.challenge.application.adapter.SpaceshipCommandService;
import com.project.challenge.application.adapter.SpaceshipQueryService;
import com.project.challenge.application.exceptions.SpaceshipNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteSpaceshipService {

    @Autowired
    private SpaceshipCommandService spaceShipCommandService;

    @Autowired
    private SpaceshipQueryService spaceshipQueryService;

    /**
     * Eliminate a spaceship given his ID
     * Throw an exception if the spaceship is not found
     *
     * @param id
     * @return void
     * @throws SpaceshipNotFound
     */
    @CacheEvict(value = "spaceship", key = "#id")
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) throws SpaceshipNotFound {
        spaceshipQueryService.findSpaceship(id).orElseThrow(SpaceshipNotFound::new);
        spaceShipCommandService.delete(id);
    }
}
