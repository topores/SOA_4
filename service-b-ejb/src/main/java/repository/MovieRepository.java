package repository;

import dto.MovieGenre;
import model.Movie;
import model.Person;

import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RequestScoped
public class MovieRepository extends AbstractCrudRepository<Movie> {
    public MovieRepository() {
        super(Movie.class);
    }

    public Long countMoviesByOscarsLessThen(long amount) {
        CriteriaQuery<Long> countQuery = createCountQuery();
        countQuery.where(criteriaBuilder.greaterThanOrEqualTo(getEntityRoot().get("oscarsCount"), amount));

        return em.createQuery(countQuery).getSingleResult();

    }

    public Long countMoviesByGenre(MovieGenre genre) {
        CriteriaQuery<Long> countQuery = createCountQuery();
        countQuery.where(criteriaBuilder.equal(getEntityRoot().get("genre"), genre));

        return em.createQuery(countQuery).getSingleResult();
    }

    public List<Movie> findAllByGenre(String genre) {
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> root = criteriaQuery.from(Movie.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("genre"), MovieGenre.valueOf(genre)));
        return session.createQuery(criteriaQuery).getResultList();
    }

    public List<Movie> findAllByDirectorId(Integer id) {
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> root = criteriaQuery.from(Movie.class);

        CriteriaQuery<Person> personCriteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = personCriteriaQuery.from(Person.class);
        personCriteriaQuery.select(personRoot).where(criteriaBuilder.equal(personRoot.get("id"), id));

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("director"), session.createQuery(personCriteriaQuery).getSingleResult()));
        return session.createQuery(criteriaQuery).getResultList();
    }
}
