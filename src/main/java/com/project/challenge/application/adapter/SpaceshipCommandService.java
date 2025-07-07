package com.project.challenge.application.adapter;

import com.project.challenge.domain.model.Spaceship;

public interface SpaceshipCommandService {

    Spaceship save(Spaceship spaceship);

    Spaceship update(Spaceship spaceship);

    void delete(Integer id);
}
