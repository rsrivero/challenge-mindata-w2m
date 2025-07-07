package com.project.challenge.infrastructure.persistence;

import com.project.challenge.infrastructure.persistence.entity.SpaceshipEntity;
import org.springframework.data.jpa.domain.Specification;

public class SpaceshipSpecification {

    public static Specification<SpaceshipEntity> getName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}
