package com.example.soa_2.controller;

import dto.MovieDto;
import dto.PersonDto;
import paging.Page;
import paging.Pageable;
import remote.RemoteClientService;


import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/oscar")
@Produces(MediaType.APPLICATION_JSON)
public class ClientController {

    private RemoteClientService service;

    {
        try {
            service = (RemoteClientService) new InitialContext()
                    .lookup("java:global/service-a-ejb-1.0-SNAPSHOT/RemoteClientServiceImpl!remote.RemoteClientService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/screenwriters/losers")
    public Page<PersonDto> findLosers(@BeanParam Pageable pageable) {
        return Page.of(service.findLosers(), pageable);
    }

    @PUT
    @Path("/directors/humiliate-by-genre/{genre}")
    public Page<MovieDto> humiliation(@PathParam("genre") String genre,
                                      @BeanParam Pageable pageable) {
        return Page.of(service.humiliate(genre), pageable);
    }
}
