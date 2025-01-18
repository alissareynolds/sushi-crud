package com.example.sushi_practice_crud.service;

import com.example.sushi_practice_crud.exceptions.SushiNotFoundException;
import com.example.sushi_practice_crud.model.Sushi;
import com.example.sushi_practice_crud.repository.SushiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SushiService {

    private final SushiRepository sushiRepository;

    public SushiService(SushiRepository sushiRepository) {
        this.sushiRepository = sushiRepository;
    }

    public Sushi create(Sushi sushi) {
        return sushiRepository.save(sushi);
    }

    public List<Sushi> getAll() {
        return sushiRepository.findAll();
    }

    public Sushi getById(UUID id) {
        Optional<Sushi> optionalSushi = sushiRepository.findById(id);
        if (optionalSushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with id: " + id + " was not found.");
        }
        return optionalSushi.get();
    }

    public List<Sushi> getByType(String sushiType) {
       return sushiRepository.findBySushiType(sushiType);
    }

    public Sushi update(Sushi sushi, UUID id) {
        Optional<Sushi> optionalSushi = sushiRepository.findById(id);
        if (optionalSushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with id: " + id + " was not found.");
        }
        sushi.setId(id);
        return sushiRepository.save(sushi);
    }

    public Sushi patch(Sushi sushi, UUID id) {
        Optional<Sushi> optionalSushi = sushiRepository.findById(id);
        if (optionalSushi.isEmpty()) {
            throw new SushiNotFoundException("A sushi roll with id: " + id + " was not found.");
        }
        Sushi updatedSushi = optionalSushi.get();
        if (sushi.getSushiType() != null) {
            updatedSushi.setSushiType(sushi.getSushiType());
        }
        if (sushi.getFishType() != null) {
            updatedSushi.setFishType(sushi.getFishType());
        }
        if (sushi.getIsSpicy() != null) {
            updatedSushi.setIsSpicy(sushi.getIsSpicy());
        }
        if (sushi.getIsDeconstructed() != null) {
            updatedSushi.setIsDeconstructed(sushi.getIsDeconstructed());
        }
        if (sushi.getNumberOfRolls() != null) {
            updatedSushi.setNumberOfRolls(sushi.getNumberOfRolls());
        }
        return sushiRepository.save(updatedSushi);
    }

    public void delete(UUID id) {
        sushiRepository.deleteById(id);
    }


}
