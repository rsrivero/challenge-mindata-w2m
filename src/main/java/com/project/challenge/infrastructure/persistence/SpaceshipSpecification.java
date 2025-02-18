package com.project.challenge.infrastructure.persistence;

import com.project.challenge.domain.entity.Spaceship;
import org.springframework.data.jpa.domain.Specification;

public class SpaceshipSpecification {

    public static Specification<Spaceship> getName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
