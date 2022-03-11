package com.example.soa_2.controller;

import dto.MovieDto;
import dto.MovieDto;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import paging.Page;
import paging.Pageable;
import remote.RemoteMovieService;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieController {

    private RemoteMovieService service;

    {
        try {
            service = (RemoteMovieService) new InitialContext()
                    .lookup("java:global/service-b-ejb-1.0-SNAPSHOT/RemoteMovieServiceImpl!remote.RemoteMovieService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/{id}")
    public MovieDto getById(@PathParam("id") Integer id) {
        return service.getById(id);
    }

    @PUT
    @Path("/{id}")
    public MovieDto update(@PathParam("id") Integer id, @RequestBody MovieDto updateDto) {
        return service.update(id, updateDto);
    }

    @POST
    public MovieDto create(@RequestBody MovieDto createDto) {
        return service.create(createDto);
    }

    @GET
    public Page<MovieDto> getAll(@BeanParam Pageable pageable) {
        return Page.of(service.getAll(), pageable);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        service.deleteById(id);
    }

    @GET
    @Path("/by-genre/{genre}")
    public Page<MovieDto> findMoviesByGenre(@PathParam("genre") String genre,
                                            @BeanParam Pageable pageable) {
        return Page.of(service.findAllByGenre(genre), pageable);
    }

    @GET
    @Path("/by-director/{directorId}")
    public Page<MovieDto> findMoviesByDirectorId(@PathParam("directorId") Integer id,
                                                 @BeanParam Pageable pageable) {
        return Page.of(service.findAllByDirectorId(id), pageable);
    }

}
