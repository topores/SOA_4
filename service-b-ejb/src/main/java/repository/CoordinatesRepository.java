package repository;

import model.Coordinates;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CoordinatesRepository extends AbstractCrudRepository<Coordinates> {
    public CoordinatesRepository() {
        super(Coordinates.class);
    }
}
