package com.example.sushi_practice_crud.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "sushi")
public class Sushi {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String sushiType;

    private String fishType;

    private Boolean isSpicy;

    private Boolean isDeconstructed;

    private Integer numberOfRolls;

    public Sushi() {

    }

    public Sushi(UUID id, String sushiType, String fishType, Boolean isSpicy, Boolean isDeconstructed, Integer numberOfRolls) {
        this.id = id;
        this.sushiType = sushiType;
        this.fishType = fishType;
        this.isSpicy = isSpicy;
        this.isDeconstructed = isDeconstructed;
        this.numberOfRolls = numberOfRolls;
    }

    public Sushi(String sushiType, String fishType, Boolean isSpicy, Boolean isDeconstructed, Integer numberOfRolls) {
        this.sushiType = sushiType;
        this.fishType = fishType;
        this.isSpicy = isSpicy;
        this.isDeconstructed = isDeconstructed;
        this.numberOfRolls = numberOfRolls;
    }

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
