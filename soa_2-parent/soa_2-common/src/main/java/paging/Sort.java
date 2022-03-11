package paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sort {
    @QueryParam("sortBy")
    private String fieldName;
    @QueryParam("direction")
    @DefaultValue("ASC")
    private Direction direction;

    public static Sort DEFAULT = new Sort(null, Direction.ASC);


    @Getter
    public enum Direction {
        ASC,
        DESC
    }
}
