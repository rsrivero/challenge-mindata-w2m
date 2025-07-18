package com.project.challenge.application.usecases;

import com.project.challenge.application.adapter.SpaceshipCommandService;
import com.project.challenge.application.dto.SpaceshipDTO;
import com.project.challenge.application.mapper.SpaceshipMapper;
import com.project.challenge.domain.model.Spaceship;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveSpaceshipService {

    @Autowired
    private SpaceshipCommandService spaceShipCommandService;

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    /**
     * Save a spaceship.
     *
     * @param request SpaceshipsDTORequest
     * @return saved spaceships
     */
    public SpaceshipDTO save(Spaceship request) {
        var spaceship = spaceshipMapper.toDomain(request);
        spaceship = spaceShipCommandService.save(spaceship);
        return spaceshipMapper.toDTO(spaceship);
    }
}
