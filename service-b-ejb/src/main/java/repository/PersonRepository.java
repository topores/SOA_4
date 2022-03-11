package repository;

import model.Person;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RequestScoped
public class PersonRepository extends AbstractCrudRepository<Person> {
    public PersonRepository() {
        super(Person.class);
    }

    public List<Person> findAllByDirectorLessThan(String director) {
        CriteriaQuery<Person> criteriaQueryPerson = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQueryPerson.from(Person.class);
        criteriaQueryPerson.select(root).where(criteriaBuilder.lessThan(root.get("name"), director));

        Query<Person> query = session.createQuery(criteriaQueryPerson);
        return query.getResultList();
    }
}