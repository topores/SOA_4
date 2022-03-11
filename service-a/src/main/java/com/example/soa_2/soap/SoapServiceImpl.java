package com.example.soa_2.soap;

import dto.MovieDto;
import dto.PersonDto;
import paging.Page;
import paging.Pageable;
import remote.RemoteClientService;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@WebService(endpointInterface = "com.example.soa_2.soap.SoapService")
public class SoapServiceImpl implements SoapService {
    private RemoteClientService service;

    {
        try {
            service = (RemoteClientService) new InitialContext()
                    .lookup("java:global/service-a-ejb-1.0-SNAPSHOT/RemoteClientServiceImpl!remote.RemoteClientService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<PersonDto> findLosers(Pageable pageable) {
        return Page.of(service.findLosers(), pageable);
    }

    @Override
    public Page<MovieDto> humiliate(Pageable pageable, String genre) {
        return Page.of(service.humiliate(genre), pageable);
    }
}
