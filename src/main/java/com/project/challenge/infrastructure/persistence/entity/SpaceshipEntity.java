package com.project.challenge.infrastructure.persistence.entity;

import com.project.challenge.infrastructure.persistence.SpaceshipEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(SpaceshipEntityListener.class)
@Table(name = "SPACESHIP")
@Entity
@Getter
@Setter
public class SpaceshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
