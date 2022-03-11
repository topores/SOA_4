package service;

import dto.CountDto;
import dto.MovieDto;
import dto.MovieGenre;
import mapper.MovieMapper;
import model.Movie;
import remote.RemoteMovieService;
import repository.MovieRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RemoteMovieServiceImpl implements RemoteMovieService {

    @Inject
    MovieRepository repository;
    @Inject
    MovieMapper mapper;

    public List<MovieDto> getAll() {
        return mapper.mapEntitiesToDtos(repository.findAll());
    }

    public MovieDto getById(Integer id) {
        return mapper.mapToDto(repository.getById(id));
    }

    public MovieDto create(MovieDto createDto) {
        return mapper.mapToDto(repository.create(mapper.mapToEntity(createDto)));
    }

    public MovieDto update(Integer id, MovieDto updateDto) {
        Movie updateEntity = mapper.mapToEntity(updateDto);

        return mapper.mapToDto(repository.update(id, updateEntity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public CountDto countMoviesByOscarsLessThen(long amount) {
        return CountDto.builder()
                       .count(repository.countMoviesByOscarsLessThen(amount))
                       .build();
    }

    public CountDto countMoviesByGenre(MovieGenre genre) {
        return CountDto.builder()
                       .count(repository.countMoviesByGenre(genre))
                       .build();
    }

    public List<MovieDto> findAllByGenre(String genre) {
        return mapper.mapEntitiesToDtos(repository.findAllByGenre(genre));
    }

    public List<MovieDto> findAllByDirectorId(Integer id) {
        return mapper.mapEntitiesToDtos(repository.findAllByDirectorId(id));
    }
}
