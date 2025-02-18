package com.project.challenge.application.adapter;

import com.project.challenge.domain.entity.Spaceship;


public interface SpaceshipCommandService {

    Spaceship save(Spaceship spaceship);

    Spaceship update(Spaceship spaceship);

    void delete(Integer id);
}
