package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {
    private Integer id;
    private String name;
    private String birthday;
    private Long weight;
    private String passportId;
    private Integer locationId;
}
