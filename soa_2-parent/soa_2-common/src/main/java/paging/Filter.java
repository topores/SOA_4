package paging;

import exception.FilterIsNotSupportedException;
import exception.UnsupportedFilterOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.ws.rs.QueryParam;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    @QueryParam("filter")
    private String filterQuery;

    transient private FilterSign filterSign;
    transient private String fieldName;
    transient private Long constraint;

    private static final List<String> supportedFields = Arrays.asList(
            "weight",
            "id",
            "oscarsCount",
            "x",
            "y"
    );

    public static Filter DEFAULT = new Filter(null, null, null, null);

    public Filter toPredicate() throws FilterIsNotSupportedException {
        String[] parsed = this.getFilterQuery().split(",");
        String fieldName = parsed[0];
        if (isSupported(fieldName)) {
            return new Filter(this.filterQuery, FilterSign.fromLiteral(parsed[1]), parsed[0], Long.parseLong(parsed[2]));
        } else {
            throw new FilterIsNotSupportedException(format("[%s] is not supported for filtering", fieldName));
        }
    }

    private boolean isSupported(String fieldName) {
        return supportedFields.contains(fieldName);
    }

    public boolean compare(Object object) {
        boolean result = false;
        switch (this.getFilterSign()) {
            case EQ: {
                result = Objects.equals(object, constraint);
                break;
            }
            case GT: {
                result = ((Long) object) > constraint;
                break;
            }
            case LT: {
                result = ((Long) object) < constraint;
                break;
            }
        }
        return result;
    }

    @Getter
    public enum FilterSign {
        EQ("="),
        GT(">"),
        LT("<");

        private final String literal;

        FilterSign(String literal) {
            this.literal = literal;
        }

        public static FilterSign fromLiteral(String literal) {
            return Arrays.stream(FilterSign.values())
                         .filter(v -> v.getLiteral().equals(literal))
                         .findFirst().orElseThrow(() -> {
                        throw new UnsupportedFilterOperation(format("%s is not supported", literal));
                    });
        }
    }
}