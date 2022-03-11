package remote;

import dto.LocationDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteLocationService {
    public List<LocationDto> getAll();

    public LocationDto getById(Integer id);

    public LocationDto create(LocationDto createDto);

    public LocationDto update(Integer id, LocationDto updateDto);

    public void deleteById(Integer id);
}
