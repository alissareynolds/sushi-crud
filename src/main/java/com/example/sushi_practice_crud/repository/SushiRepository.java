package com.example.sushi_practice_crud.repository;

import com.example.sushi_practice_crud.model.Sushi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SushiRepository extends JpaRepository<Sushi, UUID> {
    Optional<Sushi> findBySushiType(String sushiType);
}
