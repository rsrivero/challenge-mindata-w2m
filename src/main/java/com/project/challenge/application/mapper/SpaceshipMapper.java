package com.project.challenge.application.mapper;

import com.project.challenge.application.dto.SpaceshipDTO;
import com.project.challenge.domain.entity.Spaceship;
import com.project.challenge.infrastructure.rest.request.SpaceshipDTORequest;
import com.project.challenge.infrastructure.rest.response.SpaceshipDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpaceshipMapper {

    Spaceship toEntity(SpaceshipDTORequest request);

    SpaceshipDTO toDTO(Spaceship spaceship);

    SpaceshipDTOResponse toResponse(SpaceshipDTO spaceship);

    Spaceship updateEntity(@MappingTarget Spaceship spaceship, SpaceshipDTORequest updateSpaceship);

}
