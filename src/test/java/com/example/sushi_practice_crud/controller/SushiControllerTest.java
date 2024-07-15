package com.example.sushi_practice_crud.controller;

import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.service.SushiService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SushiControllerTest {

    private SushiController sushiController;
    private SushiService mockSushiService;

    // represents what the user gives us before sushi is saved in database
    private final Sushi input = new Sushi(null,"lion king roll", "salmon", true, false, 8);
    // represents the sushi after it is saved in the database
    private final Sushi input2 = new Sushi(UUID.randomUUID(), "lion king roll", "salmon", true, false, 8);

    @BeforeEach
    public void setup() {
        mockSushiService = Mockito.mock(SushiService.class);
        sushiController = new SushiController(mockSushiService);
    }

    @Test
    public void createSushi_shouldReturnSushiAndCREATEDHttpStatus() {
        // arrange
        Mockito.when(mockSushiService.create(Mockito.any())).thenReturn(input2);
        // act
        ResponseEntity<Sushi> response = sushiController.createSushi(input);
        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getSushiById_shouldReturnSushiAndOKHttpStatus() {
        // arrange
        Mockito.when(mockSushiService.findSushiById(Mockito.any())).thenReturn(input2);
        // act
        ResponseEntity<Sushi> response = sushiController.getSushiById(input2.getId());
        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}