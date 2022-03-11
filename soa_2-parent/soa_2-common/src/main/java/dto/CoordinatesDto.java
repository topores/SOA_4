package dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoordinatesDto implements Serializable {
    private Float x;
    private Long y;
}
