package com.example.sushi_practice_crud.controller;

import com.example.sushi_practice_crud.exceptions.SushiNotFoundException;
import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.service.SushiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sushi")
public class SushiController {

    private final SushiService sushiService;

    public SushiController(SushiService sushiService) {
        this.sushiService = sushiService;
    }

    @PostMapping
    public ResponseEntity<Sushi> createSushi(@RequestBody Sushi sushi) {
        Sushi newSushi = sushiService.create(sushi);
        return new ResponseEntity<>(newSushi, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sushi>> getAllSushi() {
        List<Sushi> sushiList = sushiService.getAll();
        return new ResponseEntity<>(sushiList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sushi> getSushiById(@PathVariable UUID id) {
        Sushi sushi;
        try {
            sushi = sushiService.getById(id);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sushi, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Sushi>> getSushiByType(@PathVariable String sushiType) {
        List<Sushi> sushiList = sushiService.getByType(sushiType);
        return new ResponseEntity<>(sushiList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sushi> updateSushi(@RequestBody Sushi sushi, @PathVariable UUID id) {
        Sushi newSushi;
        try {
            newSushi = sushiService.update(sushi, id);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newSushi, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sushi> patchSushi(@RequestBody Sushi sushi, @PathVariable UUID id) {
        Sushi newSushi;
        try {
            newSushi = sushiService.patch(sushi, id);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newSushi, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Sushi> deleteSushi(@PathVariable UUID id) {
        sushiService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
