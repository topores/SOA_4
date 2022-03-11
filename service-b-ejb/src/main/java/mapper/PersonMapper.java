package mapper;

import model.Person;
import repository.LocationRepository;
import dto.PersonDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Mapper(componentModel = "CDI")
public abstract class PersonMapper extends BasicEntityDtoMapper<PersonDto, Person> {

    @Inject
    private LocationRepository repository;

    private final String dateFormat = "dd.MM.yyyy";

    @Override
    @Mapping(source = "birthday", target = "birthday", dateFormat = dateFormat)
    public abstract PersonDto mapToDto(Person personDto);

    @Override
    @Mapping(source = "birthday", target = "birthday", dateFormat = dateFormat)
    public abstract Person mapToEntity(PersonDto person);

    @Override
    @Mapping(source = "birthday", target = "birthday", dateFormat = dateFormat)
    public abstract List<PersonDto> mapEntitiesToDtos(List<Person> personDtoList);

    @Override
    @Mapping(source = "birthday", target = "birthday", dateFormat = dateFormat)
    public abstract List<Person> mapDtosToEntities(List<PersonDto> people);

    @AfterMapping
    public void afterMapToEntity(@MappingTarget Person entity, PersonDto dto) {
        repository.findById(dto.getLocationId())
                  .ifPresent(entity::setLocation);
    }

    @AfterMapping
    public void afterMapToDto(@MappingTarget PersonDto dto, Person entity) {
        dto.setLocationId(entity.getLocation().getId());
    }
}
