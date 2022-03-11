package service;

import dto.CoordinatesDto;
import mapper.CoordinatesMapper;
import model.Coordinates;
import remote.RemoteCoordinatesService;
import repository.CoordinatesRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RemoteCoordinatesServiceImpl implements RemoteCoordinatesService {

    @Inject
    CoordinatesRepository repository;
    @Inject
    CoordinatesMapper mapper;


    public List<CoordinatesDto> getAll() {
        return mapper.mapEntitiesToDtos(repository.findAll());
    }

    public CoordinatesDto getById(Integer id) {
        return mapper.mapToDto(repository.getById(id));
    }

    public CoordinatesDto create(CoordinatesDto createDto) {
        return mapper.mapToDto(repository.create(mapper.mapToEntity(createDto)));
    }

    public CoordinatesDto update(Integer id, CoordinatesDto updateDto) {
        Coordinates updateEntity = mapper.mapToEntity(updateDto);

//        if (classRegistry.contains(updateEntity.getClass()))
//            ((IdableClass) updateEntity).setId(id);

        return mapper.mapToDto(repository.update(id, updateEntity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
