package com.project.challenge.infrastructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.challenge.domain.entity.Spaceship;
import com.project.challenge.infrastructure.adapters.inbound.handlers.SpaceshipEventPublisher;
import com.project.challenge.infrastructure.interfaces.events.dto.SpaceshipActionEvent;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipEntityListener {

    @Autowired
    private SpaceshipEventPublisher spaceshipEventPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @PostPersist
    public void onPostPersist(Spaceship spaceship) throws JsonProcessingException {
        spaceshipEventPublisher.publish(new SpaceshipActionEvent("CREATED", objectMapper.writeValueAsString(spaceship)));
    }

    @PostUpdate
    public void onPostUpdate(Spaceship spaceship) throws JsonProcessingException {
        spaceshipEventPublisher.publish(new SpaceshipActionEvent("UPDATED", objectMapper.writeValueAsString(spaceship)));
    }

    @PostRemove
    public void onPostRemove(Spaceship spaceship) throws JsonProcessingException {
        spaceshipEventPublisher.publish(new SpaceshipActionEvent("DELETED", objectMapper.writeValueAsString(spaceship)));
    }
}
