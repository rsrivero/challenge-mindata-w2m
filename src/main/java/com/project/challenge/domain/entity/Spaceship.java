package com.project.challenge.domain.entity;

import com.project.challenge.infrastructure.persistence.SpaceshipEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(SpaceshipEntityListener.class)  // Aquí vinculamos el listener
@Table
@Entity
@Getter
@Setter
public class Spaceship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
