package com.project.challenge.infrastructure.persistence.mapper;

import com.project.challenge.domain.model.Spaceship;
import com.project.challenge.infrastructure.persistence.entity.SpaceshipEntity;

public class SpaceshipDomainMapper {

    public static Spaceship toDomain(SpaceshipEntity entity) {
        return new Spaceship(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    public static SpaceshipEntity toEntity(Spaceship spaceship) {
        SpaceshipEntity entity = new SpaceshipEntity();
        entity.setId(spaceship.getId());
        entity.setName(spaceship.getName());
        entity.setDescription(spaceship.getDescription());
        return entity;
    }
}

