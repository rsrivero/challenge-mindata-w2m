package com.project.challenge.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpaceshipDTOResponse {

    private Integer id;

    private String name;

    private String description;
}
