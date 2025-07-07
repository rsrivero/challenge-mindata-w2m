package com.project.challenge.application.mapper;

import com.project.challenge.application.dto.SpaceshipDTO;
import com.project.challenge.domain.model.Spaceship;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpaceshipMapper {

    Spaceship toDomain(Spaceship request);

    SpaceshipDTO toDTO(Spaceship spaceship);

    Spaceship updateDomain(@MappingTarget Spaceship spaceship, Spaceship updateSpaceship);

}
