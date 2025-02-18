package com.project.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.challenge.service.SpaceshipFactory;
import com.project.challenge.service.SpaceshipRequestBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpaceshipCrudControllerTest {

    @Autowired
    private SpaceshipRequestBuilder spaceshipRequestBuilder;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SpaceshipFactory spaceshipFactory;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String spaceshipPath = "/v1/spaceship";

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void test_Create_Should_Create_When_CreateSpaceship() throws Exception {
        var req = spaceshipRequestBuilder.build();
        this.mockMvc.perform(
                post(spaceshipPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo(req.getName())))
                .andExpect(jsonPath("$.description", equalTo(req.getDescription())));

    }

    @Test
    public void test_Find_Should_FindSpaceship_When_SpaceshipIdFound() throws Exception {
        var spaceship = spaceshipFactory.create();
        var spaceshipFindId = spaceshipPath.concat("/").concat(String.valueOf(spaceship.getId()));

        this.mockMvc.perform(
                        get(spaceshipFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(spaceship.getId())))
                .andExpect(jsonPath("$.name", equalTo(spaceship.getName())))
                .andExpect(jsonPath("$.description", equalTo(spaceship.getDescription())));

    }

    @Test
    public void test_Find_Should_NotFount_When_spaceshipIdNotFound() throws Exception {
        var spaceshipFindId = spaceshipPath.concat("/")
                .concat("498498494");

        this.mockMvc.perform(
                        get(spaceshipFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_Delete_Should_Delete_When_SpaceshipIdFound() throws Exception {
        var spaceship = spaceshipFactory.create();

        var spaceshipFindId = spaceshipPath.concat("/")
                .concat(String.valueOf(spaceship.getId()));

        this.mockMvc.perform(
                        delete(spaceshipFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void test_update_Should_update_When_SpaceshipIdFound() throws Exception {
        var spaceship = spaceshipFactory.create();

        var spaceshipFindId = spaceshipPath.concat("/")
                .concat(String.valueOf(spaceship.getId()));

        spaceship.setName("spaceship");

        this.mockMvc.perform(
                        put(spaceshipFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(spaceship)))
                .andDo(print())
                .andExpect( status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo(spaceship.getName())))
                .andExpect(jsonPath("$.description", equalTo(spaceship.getDescription())));
    }
}
