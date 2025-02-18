package com.project.challenge.application.usecases;

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

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FindSpaceshipServiceTest {

    @InjectMocks
    private FindSpaceshipService findSpaceshipService;

    @Mock
    private SpaceshipQueryService spaceshipQueryService;

    @Spy
    private SpaceshipMapper mapper = Mappers.getMapper(SpaceshipMapper.class);

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    private SpaceshipRequestBuilder spaceshipRequestBuilder = new SpaceshipRequestBuilder();


    @Test
    void findSpaceship() throws SpaceshipNotFound {
        var spaceshipRequest = spaceshipRequestBuilder.build();
        var spaceshipEntity = spaceshipMapper.toEntity(spaceshipRequest);

        Mockito.when(spaceshipQueryService.findSpaceship(Mockito.any()))
                .thenReturn(Optional.ofNullable(spaceshipEntity));

        var spaceshipResult = findSpaceshipService.findSpaceship(1);

        assertEquals(spaceshipRequest.getName(), spaceshipResult.getName());
        assertEquals(spaceshipRequest.getDescription(), spaceshipResult.getDescription());
    }

    @Test
    void findAll() {
    }
}