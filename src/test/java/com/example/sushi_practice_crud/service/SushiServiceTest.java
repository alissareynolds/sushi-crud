package com.example.sushi_practice_crud.service;

import com.example.sushi_practice_crud.exceptions.SushiNotFoundException;
import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.repository.SushiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SushiServiceTest {

    private SushiService sushiService;
    private SushiRepository mockSushiRepository;


    public final Sushi input = new Sushi(null, "Lion King", "salmon", false, false, 12);
    public final Sushi input2 = new Sushi(null, "Philly", "tuna", true, true, 0);
    public final Sushi recordWithId = new Sushi(UUID.randomUUID(), "Lion King", "salmon", false, false, 12);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockSushiRepository = Mockito.mock(SushiRepository.class);
        sushiService = new SushiService(mockSushiRepository);
    }

    @Test
    public void create_shouldReturnCreatedSushi() {
        Mockito.when(mockSushiRepository.save(Mockito.any())).thenReturn(recordWithId);
        Sushi response = sushiService.create(input);
        assertEquals(recordWithId, response);
    }

    @Test
    public void getAll_shouldReturnListOfSushi() {
        List<Sushi> sushiList = new ArrayList<>();
        sushiList.add(input);
        sushiList.add(input2);
        Mockito.when(mockSushiRepository.findAll()).thenReturn(sushiList);
        List<Sushi> response = sushiService.getAll();
        assertEquals(sushiList, response);
    }

    @Test
    public void getById_shouldReturnSushi() {
        Mockito.when(mockSushiRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Sushi response = sushiService.getById(recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void getById_throwsExceptionWhenSushiWasNotFound() {
        Mockito.when(mockSushiRepository.findById(id)).thenReturn(Optional.empty());
        SushiNotFoundException exception = assertThrows(SushiNotFoundException.class, () -> sushiService.getById(id));
        assertEquals("A sushi roll with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void getByType_shouldReturnListOfSushi() {
        Mockito.when(mockSushiRepository.findBySushiType(recordWithId.getSushiType())).thenReturn(List.of(recordWithId));
        List<Sushi> response = sushiService.getByType(recordWithId.getSushiType());
        assertEquals(List.of(recordWithId), response);
    }

    @Test
    public void update_shouldReturnUpdatedSushi() {
        Mockito.when(mockSushiRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockSushiRepository.save(Mockito.any())).thenReturn(recordWithId);
        Sushi response = sushiService.update(input2, recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void update_throwsExceptionWhenSushiWasNotFound() {
        Mockito.when(mockSushiRepository.findById(id)).thenReturn(Optional.empty());
        SushiNotFoundException exception = assertThrows(SushiNotFoundException.class, () -> sushiService.update(input, id));
        assertEquals("A sushi roll with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_throwsExceptionWhenSushiWasNotFound() {
        Mockito.when(mockSushiRepository.findById(id)).thenReturn(Optional.empty());
        SushiNotFoundException exception = assertThrows(SushiNotFoundException.class, () -> sushiService.patch(input, id));
        assertEquals("A sushi roll with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_shouldReturnUpdatedSushiType() {
        Sushi input = new Sushi();
        input.setSushiType("Philly");
        Mockito.when(mockSushiRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockSushiRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Sushi response = sushiService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Philly", response.getSushiType());
        assertEquals("salmon", response.getFishType());
        assertEquals(false, response.getIsSpicy());
        assertEquals(false , response.getIsDeconstructed());
        assertEquals(12, response.getNumberOfRolls());
    }

    @Test
    public void patch_shouldReturnUpdatedFishType() {
        Sushi input = new Sushi();
        input.setFishType("tuna");
        Mockito.when(mockSushiRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockSushiRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Sushi response = sushiService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Lion King", response.getSushiType());
        assertEquals("tuna", response.getFishType());
        assertEquals(false, response.getIsSpicy());
        assertEquals(false , response.getIsDeconstructed());
        assertEquals(12, response.getNumberOfRolls());
    }

    @Test
    public void patch_shouldReturnUpdatedIsSpicy() {
        Sushi input = new Sushi();
        input.setIsSpicy(true);
        Mockito.when(mockSushiRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockSushiRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Sushi response = sushiService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Lion King", response.getSushiType());
        assertEquals("salmon", response.getFishType());
        assertEquals(true, response.getIsSpicy());
        assertEquals(false , response.getIsDeconstructed());
        assertEquals(12, response.getNumberOfRolls());
    }

    @Test
    public void patch_shouldReturnUpdatedIsDeconstructed() {
        Sushi input = new Sushi();
        input.setIsDeconstructed(true);
        Mockito.when(mockSushiRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockSushiRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Sushi response = sushiService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Lion King", response.getSushiType());
        assertEquals("salmon", response.getFishType());
        assertEquals(false, response.getIsSpicy());
        assertEquals(true, response.getIsDeconstructed());
        assertEquals(12, response.getNumberOfRolls());
    }

    @Test
    public void patch_shouldReturnUpdatedNumberOfRolls() {
        Sushi input = new Sushi();
        input.setNumberOfRolls(0);
        Mockito.when(mockSushiRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockSushiRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Sushi response = sushiService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Lion King", response.getSushiType());
        assertEquals("salmon", response.getFishType());
        assertEquals(false, response.getIsSpicy());
        assertEquals(false, response.getIsDeconstructed());
        assertEquals(0, response.getNumberOfRolls());
    }

    @Test
    public void delete_callsRepositoryDeleteMethod() {
        sushiService.delete(id);
        Mockito.verify(mockSushiRepository).deleteById(id);
    }
}