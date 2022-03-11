package com.example.soa_2.controller;

import dto.CoordinatesDto;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import paging.Page;
import paging.Pageable;
import remote.RemoteClientService;
import remote.RemoteCoordinatesService;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/coordinates")
@Produces(MediaType.APPLICATION_JSON)
public class CoordinatesController {

    private RemoteCoordinatesService service;

    {
        try {
            service = (RemoteCoordinatesService) new InitialContext()
                    .lookup("java:global/service-b-ejb-1.0-SNAPSHOT/RemoteCoordinatesServiceImpl!remote.RemoteCoordinatesService");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/{id}")
    public CoordinatesDto getById(@PathParam("id") Integer id) {
        return service.getById(id);
    }

    @PUT
    @Path("/{id}")
    public CoordinatesDto update(@PathParam("id") Integer id, @RequestBody CoordinatesDto updateDto) {
        return service.update(id, updateDto);
    }

    @POST
    public CoordinatesDto create(@RequestBody CoordinatesDto createDto) {
        return service.create(createDto);
    }

    @GET
    public Page<CoordinatesDto> getAll(@BeanParam Pageable pageable) {
        return Page.of(service.getAll(), pageable);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        service.deleteById(id);
    }

}

