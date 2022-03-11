package dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDto implements Serializable {
    private Integer id;

    private String name;
    private Long oscarsCount;

    private String genre;
    private String rating;

    private Integer coordinatesId;
    private Integer directorId;

}
