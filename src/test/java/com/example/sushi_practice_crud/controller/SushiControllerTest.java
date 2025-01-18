package com.example.sushi_practice_crud.controller;

import com.example.sushi_practice_crud.exceptions.SushiNotFoundException;
import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.service.SushiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SushiControllerTest {

    private SushiController sushiController;
    private SushiService mockSushiService;

    public final Sushi input = new Sushi(null, "Lion King", "salmon", false, false, 12);
    public final Sushi input2 = new Sushi(null, "Philly", "tuna", true, true, 0);
    public final Sushi recordWithId = new Sushi(UUID.randomUUID(), "Lion King", "salmon", false, false, 12);
    public final Sushi recordWithId2 = new Sushi(recordWithId.getId(), "Philly", "tuna", true, true, 0);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockSushiService = Mockito.mock(SushiService.class);
        sushiController = new SushiController(mockSushiService);
    }

    @Test
    public void createSushi_shouldReturnSushiAndCREATEDHttpStatus() {
        Mockito.when(mockSushiService.create(Mockito.any())).thenReturn(recordWithId);
        ResponseEntity<Sushi> response = sushiController.createSushi(input);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getAllSushi_shouldReturnListOfSushiAndOKHttpStatus() {
        List<Sushi> sushiList = new ArrayList<>();
        sushiList.add(input);
        sushiList.add(input2);
        Mockito.when(mockSushiService.getAll()).thenReturn(sushiList);
        ResponseEntity<List<Sushi>> response = sushiController.getAllSushi();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sushiList, response.getBody());
    }

    @Test
    public void getSushiById_shouldReturnSushiAndOKHttpStatus() {
        Mockito.when(mockSushiService.getById(recordWithId.getId())).thenReturn(recordWithId);
        ResponseEntity<Sushi> response = sushiController.getSushiById(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getSushiById_shouldReturn404WhenSushiNotFound() {
        Mockito.when(mockSushiService.getById(id)).thenThrow(new SushiNotFoundException("A sushi roll with id: " + id + " was not found."));
        ResponseEntity<Sushi> response = sushiController.getSushiById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getSushiByType_shouldReturnListOfSushiAndOKHttpStatus() {
        Mockito.when(mockSushiService.getByType(recordWithId.getSushiType())).thenReturn(List.of(recordWithId));
        ResponseEntity<List<Sushi>> response = sushiController.getSushiByType(recordWithId.getSushiType());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(recordWithId), response.getBody());
    }

    @Test
    public void updateSushi_shouldReturnSushiAndOKHttpStatus() {
        Mockito.when(mockSushiService.update(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Sushi> response = sushiController.updateSushi(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void updateSushi_shouldReturn404WhenSushiNotFound() {
        Mockito.when(mockSushiService.update(input, id)).thenThrow(new SushiNotFoundException("A sushi roll with id: " + id + " was not found."));
        ResponseEntity<Sushi> response = sushiController.updateSushi(input, id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void patchSushi_shouldReturnSushiAndOKHttpStatus() {
        Mockito.when(mockSushiService.patch(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Sushi> response = sushiController.patchSushi(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void patchSushi_shouldReturn404WhenSushiNotFound() {
        Mockito.when(mockSushiService.patch(input, id)).thenThrow(new SushiNotFoundException("A sushi roll with id: " + id + " was not found."));
        ResponseEntity<Sushi> response = sushiController.patchSushi(input, id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteSushi_shouldReturnOKHttpStatus() {
        ResponseEntity<Sushi> response = sushiController.deleteSushi(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}