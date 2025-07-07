package com.project.challenge.infrastructure.mapper;

import com.project.challenge.domain.model.Spaceship;
import com.project.challenge.infrastructure.rest.request.SpaceshipDTORequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceshipRequestMapper {

    Spaceship toDomain(SpaceshipDTORequest commandDTO);

}

