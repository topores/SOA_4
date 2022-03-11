package remote;

import dto.CoordinatesDto;
import dto.MovieDto;
import dto.PersonDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteCoordinatesService {
    public List<CoordinatesDto> getAll();

    public CoordinatesDto getById(Integer id);

    public CoordinatesDto create(CoordinatesDto createDto);

    public CoordinatesDto update(Integer id, CoordinatesDto updateDto);

    public void deleteById(Integer id);
}
