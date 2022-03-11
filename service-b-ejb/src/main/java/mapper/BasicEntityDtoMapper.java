package mapper;

import java.util.List;

public abstract class BasicEntityDtoMapper<DTO, ENTITY> {
    public abstract DTO mapToDto(ENTITY entity);

    public abstract ENTITY mapToEntity(DTO dto);

    public abstract List<DTO> mapEntitiesToDtos(List<ENTITY> entities);

    public abstract List<ENTITY> mapDtosToEntities(List<DTO> dtos);
}
