package mapper;

import dto.LocationDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import model.Location;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-05T12:43:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@ApplicationScoped
public class LocationMapperImpl extends LocationMapper {

    @Override
    public LocationDto mapToDto(Location entity) {
        if ( entity == null ) {
            return null;
        }

        LocationDto locationDto = new LocationDto();

        locationDto.setX( entity.getX() );
        locationDto.setY( entity.getY() );
        locationDto.setName( entity.getName() );

        return locationDto;
    }

    @Override
    public Location mapToEntity(LocationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Location location = new Location();

        location.setX( dto.getX() );
        location.setY( dto.getY() );
        location.setName( dto.getName() );

        return location;
    }

    @Override
    public List<LocationDto> mapEntitiesToDtos(List<Location> entities) {
        if ( entities == null ) {
            return null;
        }

        List<LocationDto> list = new ArrayList<LocationDto>( entities.size() );
        for ( Location location : entities ) {
            list.add( mapToDto( location ) );
        }

        return list;
    }

    @Override
    public List<Location> mapDtosToEntities(List<LocationDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Location> list = new ArrayList<Location>( dtos.size() );
        for ( LocationDto locationDto : dtos ) {
            list.add( mapToEntity( locationDto ) );
        }

        return list;
    }
}
