package com.project.challenge.service;

import com.project.challenge.infrastructure.rest.request.SpaceshipDTORequest;
import org.springframework.stereotype.Service;

@Service
public class SpaceshipRequestBuilder {

    public SpaceshipDTORequest build() {
        return SpaceshipDTORequest.builder()
                .name("USS Enterprise NCC-1701")
                .description("La nave de Star Trek.")
                .build();

    }
}
