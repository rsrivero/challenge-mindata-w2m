package com.project.challenge.service;

import com.project.challenge.application.dto.SpaceshipDTO;
import com.project.challenge.application.usecases.SaveSpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceshipFactory {
    @Autowired
    private SaveSpaceshipService saveSpaceshipService;
    @Autowired
    private SpaceshipRequestBuilder spaceshipRequestBuilder;

    public SpaceshipDTO create() {
        var req = spaceshipRequestBuilder.build();
        return saveSpaceshipService.save(req);
    }
}
