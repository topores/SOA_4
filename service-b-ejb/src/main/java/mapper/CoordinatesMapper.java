package mapper;

import model.Coordinates;
import dto.CoordinatesDto;
import org.mapstruct.Mapper;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

@Mapper(componentModel = "CDI")
public abstract class CoordinatesMapper extends BasicEntityDtoMapper<CoordinatesDto, Coordinates> {
}
