package remote;

import dto.CoordinatesDto;
import dto.MovieDto;
import dto.PersonDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteClientService {
    public List<PersonDto> findLosers();

    public List<MovieDto> humiliate(String genre);


}
