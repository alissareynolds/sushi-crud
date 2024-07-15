package com.example.sushi_practice_crud.service;

import com.example.sushi_practice_crud.exceptions.SushiNotFoundException;
import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.repository.SushiRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SushiService {

    @Autowired
    SushiRepository sushiRepository;

    public Sushi create(Sushi sushi) {
        Sushi newSushi = new Sushi(sushi.getSushiType(), sushi.getFishType(), sushi.getIsSpicy(), sushi.getIsDeconstructed(), sushi.getNumberOfRolls());
        return sushiRepository.save(newSushi);
    }

    public List<Sushi> getAllSushi() {
        return sushiRepository.findAll();
    }

    public Sushi findSushiById(UUID id) {
        Optional<Sushi> optionalSushi = sushiRepository.findById(id);
        if (optionalSushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with that id was not found.");
        }
        return optionalSushi.get();
    }

    public Sushi findSushiByType(String sushiType) {
        Optional<Sushi> optionalSushi = sushiRepository.findBySushiType(sushiType);
        if (optionalSushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with that type was not found.");
        }
        return optionalSushi.get();
    }

    public Sushi updateSushi(Sushi sushi, UUID id) {
        Optional<Sushi> optionalSushi = sushiRepository.findById(id);
        if (optionalSushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with that id was not found.");
        }
        Sushi updatedSushi = new Sushi(id, sushi.getSushiType(), sushi.getFishType(), sushi.getIsSpicy(), sushi.getIsDeconstructed(), sushi.getNumberOfRolls());
        return sushiRepository.save(updatedSushi);
    }

    public Sushi deleteSushi(UUID id) {
        Optional<Sushi> sushi = sushiRepository.findById(id);
        if (sushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with that id was not found.");
        }
        sushiRepository.delete(sushi.get());
        return sushi.get();
    }

    public Sushi patchSushiById(Sushi sushi, UUID id) {
        Optional<Sushi> optionalSushi = sushiRepository.findById(id);
        if (optionalSushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with that id was not found.");
        }
        Sushi updatedSushi = optionalSushi.get();
        if (updatedSushi.getSushiType() != null) {
            updatedSushi.setSushiType(sushi.getSushiType());
        }
        if (updatedSushi.getFishType() != null) {
            updatedSushi.setFishType(sushi.getFishType());
        }
        if (updatedSushi.getIsSpicy() != null) {
            updatedSushi.setIsDeconstructed(sushi.getIsDeconstructed());
        }
        if (updatedSushi.getNumberOfRolls() != null) {
            updatedSushi.setNumberOfRolls(sushi.getNumberOfRolls());
        }
        return sushiRepository.save(updatedSushi);
    }
}
