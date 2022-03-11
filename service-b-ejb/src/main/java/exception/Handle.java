package exception;

import javax.ws.rs.core.Response;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Handle {
    Class<? extends Throwable>[] value() default {};

    Response.Status status() default Response.Status.INTERNAL_SERVER_ERROR;
}