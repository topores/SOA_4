package repository;

import model.Location;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LocationRepository extends AbstractCrudRepository<Location> {
    public LocationRepository() {
        super(Location.class);
    }
}
