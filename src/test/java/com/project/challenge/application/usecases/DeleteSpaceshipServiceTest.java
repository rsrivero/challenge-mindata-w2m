package com.project.challenge.application.usecases;

import com.project.challenge.application.adapter.SpaceshipCommandService;
import com.project.challenge.application.adapter.SpaceshipQueryService;
import com.project.challenge.application.exceptions.SpaceshipNotFound;
import com.project.challenge.application.mapper.SpaceshipMapper;
import com.project.challenge.service.SpaceshipRequestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DeleteSpaceshipServiceTest {
    @InjectMocks
    private DeleteSpaceshipService deleteSpaceshipService;

    @Mock
    private SpaceshipQueryService spaceshipQueryService;

    @Mock
    private SpaceshipCommandService spaceShipCommandService;

    @Spy
    private SpaceshipMapper mapper = Mappers.getMapper(SpaceshipMapper.class);

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    private final SpaceshipRequestBuilder spaceshipRequestBuilder = new SpaceshipRequestBuilder();


    @Test
    void delete() throws SpaceshipNotFound {
        var spaceshipRequest = spaceshipRequestBuilder.build();
        var spaceshipEntity = spaceshipMapper.toEntity(spaceshipRequest);

        Mockito.when(spaceshipQueryService.findSpaceship(1)).thenReturn(Optional.of(spaceshipEntity));

        deleteSpaceshipService.delete(1);

        Mockito.verify(spaceShipCommandService, Mockito.times(1)).delete(1);
    }
}