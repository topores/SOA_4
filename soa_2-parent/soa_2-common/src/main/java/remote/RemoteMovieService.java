package remote;

import dto.CountDto;
import dto.MovieDto;
import dto.MovieGenre;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteMovieService {
    public List<MovieDto> getAll();

    public MovieDto getById(Integer id);

    public MovieDto create(MovieDto createDto);

    public MovieDto update(Integer id, MovieDto updateDto);

    public void deleteById(Integer id);

    List<MovieDto> findAllByGenre(String genre);

    List<MovieDto> findAllByDirectorId(Integer id);

    CountDto countMoviesByOscarsLessThen(long amount);

    CountDto countMoviesByGenre(MovieGenre genre);
}
