package com.example.soa_2.soap;

import dto.MovieDto;
import dto.PersonDto;
import paging.Page;
import paging.Pageable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SoapService {
    @WebMethod
    Page<PersonDto> findLosers(Pageable pageable);
    @WebMethod
    Page<MovieDto> humiliate(Pageable pageable, String genre);
}
