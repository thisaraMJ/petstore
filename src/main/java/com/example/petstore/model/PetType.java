package com.example.petstore.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Schema(name = "PetType")
public class PetType extends PanacheEntityBase {

    @Id
    @Schema(required = true, description = "type id")
    @JsonProperty("type_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Schema(required = true, description = "Pet type")
    @JsonProperty("pet_type")
    private String petType;

    public PetType() {}

    public PetType(int id, String petType) {
        this.id = id;
        this.petType = petType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    //******Queries******
    public static PetType findByType(String petType){
        return find("petType", petType).firstResult();
    }
}