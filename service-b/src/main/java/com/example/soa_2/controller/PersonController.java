package com.example.soa_2.controller;


import dto.PersonDto;
import dto.PersonDto;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import paging.Page;
import paging.Pageable;
import remote.RemotePersonService;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

    private RemotePersonService service;

    {
        try {
            service = (RemotePersonService) new InitialContext()
                    .lookup("java:global/service-b-ejb-1.0-SNAPSHOT/RemotePersonServiceImpl!remote.RemotePersonService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/{id}")
    public PersonDto getById(@PathParam("id") Integer id) {
        return service.getById(id);
    }

    @PUT
    @Path("/{id}")
    public PersonDto update(@PathParam("id") Integer id, @RequestBody PersonDto updateDto) {
        return service.update(id, updateDto);
    }

    @POST
    public PersonDto create(@RequestBody PersonDto createDto) {
        return service.create(createDto);
    }

    @GET
    public Page<PersonDto> getAll(@BeanParam Pageable pageable) {
        return Page.of(service.getAll(), pageable);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        service.deleteById(id);
    }

}
