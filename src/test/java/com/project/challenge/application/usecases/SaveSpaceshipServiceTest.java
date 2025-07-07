package com.project.challenge.application.usecases;

import com.project.challenge.application.adapter.SpaceshipCommandService;
import com.project.challenge.application.mapper.SpaceshipMapper;
import com.project.challenge.infrastructure.mapper.SpaceshipRequestMapper;
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


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SaveSpaceshipServiceTest {

    @InjectMocks
    private SaveSpaceshipService saveSpaceshipService;

    @Mock
    private SpaceshipCommandService spaceshipCommandService;

    @Spy
    private SpaceshipMapper mapper = Mappers.getMapper(SpaceshipMapper.class);

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    @Autowired
    private SpaceshipRequestMapper spaceshipRequestMapper;
    private SpaceshipRequestBuilder spaceshipRequestBuilder = new SpaceshipRequestBuilder();

    @Test
    void save() {
        var spaceshipDTORequest = spaceshipRequestBuilder.build();
        var spaceshipRequest = spaceshipRequestMapper.toDomain(spaceshipDTORequest);

        Mockito.when(spaceshipCommandService.save(Mockito.any()))
                .thenReturn(spaceshipRequest);

        var spaceshipResult = saveSpaceshipService.save(spaceshipRequest);

        assertEquals(spaceshipRequest.getName(), spaceshipResult.getName());
        assertEquals(spaceshipRequest.getDescription(), spaceshipResult.getDescription());
    }
}


