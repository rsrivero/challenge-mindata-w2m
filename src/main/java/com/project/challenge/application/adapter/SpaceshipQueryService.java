package com.project.challenge.application.adapter;

import com.project.challenge.domain.model.Spaceship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SpaceshipQueryService {

    Optional<Spaceship> findSpaceship(Integer id);

    Page<Spaceship> findAllPaged(Pageable pageable);

    List<Spaceship> searchByName(String name, Pageable pageable);

}
