package com.example.petstore.controller;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.petstore.model.Pet;
import com.example.petstore.model.PetType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "View all pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets(){
		List<Pet> pets=Pet.listAll();
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "View pet by id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "pet id not found!") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		Pet pet=Pet.findById(petId);
		if(pet == null) return Response.ok("Pet id not found!").build();
		else return Response.ok(pet).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Adding new pet successful!", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Adding failed!") })
	@POST
	@Path("addnewpet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response addPet(@RequestBody Pet pet) {
		String type = pet.getPetType();
		PetType petType=PetType.findByType(type);

		if(petType == null){
			PetType petType1=new PetType(0,type);
			if (!petType1.isPersistent()) {
				petType1.persist();
			}
			petType1.persistAndFlush();
		}
		pet.persist();
		return Response.ok(pet).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete pet successful!", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet id not exist!") })
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@Path("delete/{petId}")
	public Response deletePet(@PathParam("petId") int id) {
		Pet pet = Pet.findById(id);
		if(pet != null){
			pet.delete();
			return Response.ok("Pet deleted successfully").build();
		}
		return Response.ok("Pet id does not exist").build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet successful!", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet is not exist!") })
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@Path("update")
	public Response updatePet(@RequestBody Pet pet) {
		Pet updatedPet;
		int n = Pet.update("petAge= ?1 , petName= ?2 , petType= ?3 where petId = ?4",pet.getPetAge(),pet.getPetName(),pet.getPetType(), pet.getPetId());
		if(n==0) updatedPet = null;
		else updatedPet= Pet.findById(pet.getPetId());

		if(updatedPet == null) return Response.ok("Pet ID invalid").build();
		else return Response.ok(updatedPet).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("search/name/{petName}")
	public Response getPetByName(@PathParam("petName") String name){
		Pet pet=Pet.findByName(name);
		return Response.ok(pet).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("search/age/{petAge}")
	public Response getPetByAge(@PathParam("petAge") int petAge){
		List<Pet> pet=Pet.findByAge(petAge);
		return Response.ok(pet).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("search/type/{petType}")
	public Response getPetByType(@PathParam("petType") int petType){
		List<Pet> pet=Pet.findByAge(petType);
		return Response.ok(pet).build();
	}

}
