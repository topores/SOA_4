package service;

import dto.MovieDto;
import dto.PersonDto;
import paging.Pageable;
import remote.RemoteClientService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class RemoteClientServiceImpl implements RemoteClientService {
    @Inject
    private BusinessService businessService;


    @Override
    public List<PersonDto> findLosers() {
        Stream<PersonDto> personDtoStream = businessService.findAllDirectors(Pageable.DEFAULT)
                                                           .getContent()
                                                           .stream()
                                                           .filter(director -> {
                                                               List<MovieDto> content = businessService.findMoviesByDirectorId(director.getId(), Pageable.DEFAULT)
                                                                                                       .getContent();
                                                               if (!content.isEmpty())
                                                                   return content.stream()
                                                                                 .map(MovieDto::getOscarsCount)
                                                                                 .reduce(0L, Long::sum) == 0;
                                                               return false;
                                                           });
        return personDtoStream
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> humiliate(String genre) {
        return businessService.findMoviesByGenre(genre, Pageable.DEFAULT)
                              .getContent()
                              .stream()
                              .flatMap(movie ->
                                      businessService.findMoviesByDirectorId(movie.getDirectorId(), Pageable.DEFAULT)
                                                     .getContent()
                                                     .stream()
                                                     .map(m -> {
                                                         m.setOscarsCount(m.getOscarsCount() != 0 ? 0 : m.getOscarsCount());
                                                         return businessService.updateMovie(m.getId(), m);
                                                     })
                              ).distinct()
                              .collect(Collectors.toList());
    }
}
