package exception;

import org.hibernate.exception.ConstraintViolationException;

import javax.json.bind.JsonbException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.text.ParseException;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ExceptionHandlerRegistry extends ExceptionHandler {

    @Handle(value = EntityNotFoundException.class,
            status = Response.Status.NOT_FOUND)
    protected void registerNotFound() {
    }

    @Handle(value = {
            JsonbException.class,
            IllegalArgumentException.class,
            ParseException.class,
            ConstraintViolationException.class,
            PersistenceException.class,
            FilterIsNotSupportedException.class,
            UnsupportedFilterOperation.class,
            BadRequestException.class,
            NoSuchFieldException.class
    }, status = Response.Status.BAD_REQUEST)
    protected void registerBadRequest() {
    }
}