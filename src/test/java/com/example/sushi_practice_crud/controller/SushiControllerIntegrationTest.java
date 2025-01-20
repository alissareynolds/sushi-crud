package com.example.sushi_practice_crud.controller;

import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.service.SushiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SushiControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SushiService mockSushiService;

    private final Sushi sushi = new Sushi(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"), "Lion King", "salmon", false, false, 12);

    @Test
    public void createSushi() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/sushi")
                .content(asJsonString(new Sushi(null, "Lion King", "salmon", false, false, 12)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllSushi() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/sushi").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getSushiById() throws Exception {
        Mockito.when(mockSushiService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(new Sushi());
        mvc.perform(MockMvcRequestBuilders
                .get("/api/sushi/59c47568-fde0-4dd7-9aef-03db6a962810")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getSushiByType() throws Exception {
        Mockito.when(mockSushiService.getByType("Lion King")).thenReturn(List.of(new Sushi()));
        mvc.perform(MockMvcRequestBuilders
                .get("/api/sushi/type/Lion King").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void updateSushi() throws Exception {
        Mockito.when(mockSushiService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(sushi);
        mvc.perform(MockMvcRequestBuilders
                .put("/api/sushi/59c47568-fde0-4dd7-9aef-03db6a962810")
                .content(asJsonString(sushi))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void patchSushi() throws Exception {
        Mockito.when(mockSushiService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(sushi);
        mvc.perform(MockMvcRequestBuilders
                .patch("/api/sushi/59c47568-fde0-4dd7-9aef-03db6a962810")
                .content(asJsonString(sushi))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteSushi() throws Exception {
        Mockito.when(mockSushiService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(sushi);
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/sushi/59c47568-fde0-4dd7-9aef-03db6a962810")
                .content(asJsonString(sushi))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
