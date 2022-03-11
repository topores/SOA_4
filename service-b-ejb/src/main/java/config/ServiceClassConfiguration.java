package config;

import model.Coordinates;
import model.IdableClass;
import model.Movie;
import model.Person;

import java.util.List;

public class ServiceClassConfiguration {
    protected final List<Class<? extends IdableClass>> classRegistry = List.of(
            Person.class,
            Coordinates.class,
            Movie.class
    );
}
