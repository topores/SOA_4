package paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageable {
    @DefaultValue("0")
    @QueryParam("page")
    private Integer page;

    @DefaultValue("20")
    @QueryParam("size")
    private Integer size;

    @BeanParam
    private Sort sort;

    @BeanParam
    private Filter filter;

    public static Pageable DEFAULT = new Pageable(0, Integer.MAX_VALUE, Sort.DEFAULT, Filter.DEFAULT);
}
