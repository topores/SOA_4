package com.example.soa_2.controller;

import dto.LocationDto;
import dto.LocationDto;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import paging.Page;
import paging.Pageable;
import remote.RemoteLocationService;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationController {

    private RemoteLocationService service;

    {
        try {
            service = (RemoteLocationService) new InitialContext()
                    .lookup("java:global/service-b-ejb-1.0-SNAPSHOT/RemoteLocationServiceImpl!remote.RemoteLocationService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/{id}")
    public LocationDto getById(@PathParam("id") Integer id) {
        return service.getById(id);
    }

    @PUT
    @Path("/{id}")
    public LocationDto update(@PathParam("id") Integer id, @RequestBody LocationDto updateDto) {
        return service.update(id, updateDto);
    }

    @POST
    public LocationDto create(@RequestBody LocationDto createDto) {
        return service.create(createDto);
    }

    @GET
    public Page<LocationDto> getAll(@BeanParam Pageable pageable) {
        return Page.of(service.getAll(), pageable);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        service.deleteById(id);
    }


}
