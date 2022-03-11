package remote;

import dto.PersonDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemotePersonService {
    public List<PersonDto> getAll();

    public PersonDto getById(Integer id);

    public PersonDto create(PersonDto createDto);

    public PersonDto update(Integer id, PersonDto updateDto);

    public void deleteById(Integer id);

    List<PersonDto> findAllByDirectorLessThen(String director);
}
