package com.example.sushi_practice_crud.controller;

import com.example.sushi_practice_crud.exceptions.SushiNotFoundException;
import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.service.SushiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sushi")
public class SushiController {

    private SushiService sushiService;

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
        List<Sushi> allSushi = sushiService.getAllSushi();
        return new ResponseEntity<>(allSushi, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sushi> getSushiById(@PathVariable UUID id) {
        Sushi sushi;
        try {
            sushi = sushiService.findSushiById(id);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sushi, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Sushi> getSushiByType(@PathVariable String sushiType) {
        Sushi sushi;
        try {
            sushi = sushiService.findSushiByType(sushiType);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sushi, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sushi> updateSushi(@RequestBody Sushi sushi, @PathVariable UUID id) {
        Sushi newSushi;
        try {
            newSushi = sushiService.updateSushi(sushi, id);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newSushi, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sushi> patchSushiById(@RequestBody Sushi sushi, @PathVariable UUID id) {
        Sushi newSushi;
        try {
            newSushi = sushiService.patchSushiById(sushi, id);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newSushi, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Sushi> deleteSushiById(@PathVariable UUID id) {
        Sushi sushi;
        try {
            sushi = sushiService.deleteSushi(id);
        } catch (SushiNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sushi, HttpStatus.OK);
    }
}
