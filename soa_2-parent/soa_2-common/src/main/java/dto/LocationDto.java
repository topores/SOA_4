package dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationDto implements Serializable {
    private Double x;
    private Integer y;
    private String name;

}
