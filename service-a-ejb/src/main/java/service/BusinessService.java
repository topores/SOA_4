package service;

import dto.MovieDto;
import dto.PersonDto;
import paging.Page;
import paging.Pageable;


public interface BusinessService {
    Page<PersonDto> findAllDirectors(Pageable pageable);

    Page<MovieDto> findMoviesByGenre(String genre, Pageable pageable);

    Page<MovieDto> findMoviesByDirectorId(Integer id, Pageable pageable);

    MovieDto updateMovie(Integer id, MovieDto updateDto);
}
