package service;

import dto.LocationDto;
import mapper.LocationMapper;
import mapper.LocationMapper;
import model.Location;
import model.IdableClass;
import model.Location;
import remote.RemoteCoordinatesService;
import remote.RemoteLocationService;
import repository.LocationRepository;
import repository.LocationRepository;
import dto.LocationDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RemoteLocationServiceImpl implements RemoteLocationService {
    @Inject
    LocationRepository repository;
    @Inject
    LocationMapper mapper;

    public List<LocationDto> getAll() {
        return mapper.mapEntitiesToDtos(repository.findAll());
    }

    public LocationDto getById(Integer id) {
        return mapper.mapToDto(repository.getById(id));
    }

    public LocationDto create(LocationDto createDto) {
        return mapper.mapToDto(repository.create(mapper.mapToEntity(createDto)));
    }

    public LocationDto update(Integer id, LocationDto updateDto) {
        Location updateEntity = mapper.mapToEntity(updateDto);
        return mapper.mapToDto(repository.update(id, updateEntity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
