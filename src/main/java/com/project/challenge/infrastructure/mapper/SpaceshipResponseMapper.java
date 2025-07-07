package com.project.challenge.infrastructure.mapper;

import com.project.challenge.application.dto.SpaceshipDTO;
import com.project.challenge.infrastructure.rest.response.SpaceshipDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceshipResponseMapper {

    SpaceshipDTOResponse toResponse(SpaceshipDTO dto);

}

