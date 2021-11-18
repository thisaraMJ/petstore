package com.example.petstore.controller;

import com.example.petstore.model.PetType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/pettypes")
@Produces("application/json")
public class PetTypeResource {

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "View all pet types",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @GET
    public Response getPetTypes(){
        List<PetType> petTypes = PetType.listAll();
        return Response.ok(petTypes).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type for id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No Pet type found for the id.") })
    @GET
    @Path("{id}")
    public Response getPetType(@PathParam("id") int id) {

        PetType petType=PetType.findById(id);
        if(petType == null){
            return Response.ok("Invalid id").build();
        }else {
            return Response.ok(petType).build();
        }
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type added",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Pet type adding failed.") })
    @POST
    @Path("addpettype")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPetType(@RequestBody PetType petType) {
        PetType.persist(petType);
        return Response.ok(petType).build();
    }


    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type deleted successful!",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Id does not exist!") })
    @DELETE
    @Transactional
    @Path("delete/{typeId}")
    public Response deletePetType(@PathParam("typeId") int id) {
        Boolean deleteResponse = PetType.deleteById(id);
        if(deleteResponse) return Response.ok("Pet type deleted successful!").build();
        else return Response.ok("Deletion unsuccessful!").build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Updating successful!", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Pet type does not exist!") })
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("update")
    public Response updatePet(@RequestBody PetType petType) {
        int n = PetType.update("petType= ?1 where id = ?2",petType.getPetType(),petType.getId());
        if(n == 0) return Response.ok("Pet ID invalid").build();
        else {
            PetType pt=PetType.findById(petType.getId());
            return Response.ok(pt).build();
        }
    }


}
