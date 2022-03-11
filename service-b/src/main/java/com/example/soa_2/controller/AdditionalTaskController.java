package com.example.soa_2.controller;

import dto.CountDto;
import dto.MovieGenre;
import dto.PersonDto;
import paging.Page;
import paging.Pageable;
import remote.RemoteMovieService;
import remote.RemotePersonService;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/additional")
@Produces(MediaType.APPLICATION_JSON)
public class AdditionalTaskController {

    private RemoteMovieService movieService;

    private RemotePersonService personService;

    {
        try {
            personService = (RemotePersonService) new InitialContext()
                    .lookup("java:global/service-b-ejb-1.0-SNAPSHOT/RemotePersonServiceImpl!remote.RemotePersonService");
            movieService = (RemoteMovieService) new InitialContext()
                    .lookup("java:global/service-b-ejb-1.0-SNAPSHOT/RemoteMovieServiceImpl!remote.RemoteMovieService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/count-by-oscars")
    public CountDto countMoviesByOscarsLessThen(@QueryParam("amount") Long amount) {
        return movieService.countMoviesByOscarsLessThen(amount);
    }

    @GET
    @Path("/count-by-genre")
    public CountDto countMoviesByGenre(@QueryParam("genre") MovieGenre genre) {
        return movieService.countMoviesByGenre(genre);
    }

    @GET
    @Path("/person-by-director")
    public Page<PersonDto> findAllByDirector(@QueryParam("name") String director,
                                             @BeanParam Pageable pageable) {
        return Page.of(personService.findAllByDirectorLessThen(director), pageable);
    }
}
