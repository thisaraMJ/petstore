package com.example.petstore.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Schema(name = "Pet")
public class Pet extends PanacheEntityBase {

	@Id
	@Schema(required = true, description = "type id")
	@JsonProperty("type_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int petId;

	@Schema(required = true, description = "Pet type")
	@JsonProperty("pet_type")
	private String petType;

	@Schema(required = true, description = "Pet name")
	@JsonProperty("pet_name")
	private String petName;

	@JsonProperty("pet_age")
	private Integer petAge;

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Integer getPetAge() {
		return petAge;
	}

	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}


	//******Queries******
	public static Pet findByName(String petName){
		return find("petName", petName).firstResult();
	}

	public static List<Pet> findByAge(int petAge){
		return find("petAge", petAge).list();
	}

	public static List<Pet> findByType(int petType){
		return find("petType", petType).list();
	}

}
