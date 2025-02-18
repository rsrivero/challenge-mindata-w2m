package com.project.challenge.application.adapter;

import com.project.challenge.domain.entity.Spaceship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface SpaceshipQueryService {

    Optional<Spaceship> findSpaceship(Integer id);

    Page<Spaceship> findAllPaged(Pageable pageable, Specification<Spaceship> where);


}
