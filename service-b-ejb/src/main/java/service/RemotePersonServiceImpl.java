package service;

import dto.PersonDto;
import mapper.PersonMapper;
import mapper.PersonMapper;
import model.Person;
import model.IdableClass;
import model.Person;
import remote.RemotePersonService;
import repository.PersonRepository;
import repository.PersonRepository;
import dto.PersonDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RemotePersonServiceImpl implements RemotePersonService {
    @Inject
    PersonRepository repository;
    @Inject
    PersonMapper mapper;

    public List<PersonDto> getAll() {
        return mapper.mapEntitiesToDtos(repository.findAll());
    }

    public PersonDto getById(Integer id) {
        return mapper.mapToDto(repository.getById(id));
    }

    public PersonDto create(PersonDto createDto) {
        return mapper.mapToDto(repository.create(mapper.mapToEntity(createDto)));
    }

    public PersonDto update(Integer id, PersonDto updateDto) {
        Person updateEntity = mapper.mapToEntity(updateDto);
        return mapper.mapToDto(repository.update(id, updateEntity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<PersonDto> findAllByDirectorLessThen(String director) {
        return mapper.mapEntitiesToDtos(repository.findAllByDirectorLessThan(director));
    }
}
