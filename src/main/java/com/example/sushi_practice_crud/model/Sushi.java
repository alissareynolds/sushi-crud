package com.example.sushi_practice_crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "sushi")
@NoArgsConstructor
@AllArgsConstructor
public class Sushi {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String sushiType;

    private String fishType;

    private Boolean isSpicy;

    private Boolean isDeconstructed;

    private Integer numberOfRolls;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSushiType() {
        return sushiType;
    }

    public void setSushiType(String sushiType) {
        this.sushiType = sushiType;
    }

    public String getFishType() {
        return fishType;
    }

    public void setFishType(String fishType) {
        this.fishType = fishType;
    }

    public Boolean getIsSpicy() {
        return isSpicy;
    }

    public void setIsSpicy(Boolean isSpicy) {
        this.isSpicy = isSpicy;
    }

    public Boolean getIsDeconstructed() {
        return isDeconstructed;
    }

    public void setIsDeconstructed(Boolean isDeconstructed) {
        this.isDeconstructed = isDeconstructed;
    }

    public Integer getNumberOfRolls() {
        return numberOfRolls;
    }

    public void setNumberOfRolls(Integer numberOfRolls) {
        this.numberOfRolls = numberOfRolls;
    }
}
