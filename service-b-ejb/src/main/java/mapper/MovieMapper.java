package mapper;

import model.Movie;
import repository.CoordinatesRepository;
import repository.PersonRepository;
import dto.MovieDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Mapper(componentModel = "CDI")
public abstract class MovieMapper extends BasicEntityDtoMapper<MovieDto, Movie> {

    @Inject
    private PersonRepository personRepository;
    @Inject
    private CoordinatesRepository coordinatesRepository;

    @Override
    @Mapping(source = "mpaaRating", target = "rating")
    public abstract MovieDto mapToDto(Movie movie);

    @Override
    @Mapping(source = "rating", target = "mpaaRating")
    public abstract Movie mapToEntity(MovieDto movieDto);

    @Override
    @Mapping(source = "mpaaRating", target = "rating")
    public abstract List<MovieDto> mapEntitiesToDtos(List<Movie> movies);

    @Override
    @Mapping(source = "rating", target = "mpaaRating")
    public abstract List<Movie> mapDtosToEntities(List<MovieDto> movieDtos);

    @AfterMapping
    public void afterMapToEntity(@MappingTarget Movie entity, MovieDto dto) {
        personRepository.findById(dto.getDirectorId())
                        .ifPresent(entity::setDirector);

        coordinatesRepository.findById(dto.getCoordinatesId())
                             .ifPresent(entity::setCoordinates);
    }

    @AfterMapping
    public void afterMapToDto(@MappingTarget MovieDto dto, Movie entity) {
        dto.setCoordinatesId(entity.getCoordinates().getId());
        dto.setDirectorId(entity.getDirector().getId());
    }
}
