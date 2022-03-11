package repository;

import util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public abstract class AbstractCrudRepository<ENTITY> {

    private final Class<ENTITY> type;
    protected final Session session = HibernateUtil.getSessionFactory().openSession();
    protected final EntityManager em = session.getEntityManagerFactory().createEntityManager();
    protected final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

    protected AbstractCrudRepository(Class<ENTITY> type) {
        this.type = type;
    }

    public ENTITY getById(Integer id) {
        return Optional.ofNullable(session.get(this.type, id))
                       .orElseThrow(() -> {
                           throw new EntityNotFoundException(format("%s [id=%d] not found", type.getSimpleName(), id));
                       });
    }

    public Optional<ENTITY> findById(Integer id) {
        return Optional.ofNullable(getById(id));
    }

    public List<ENTITY> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ENTITY> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<ENTITY> from = criteriaQuery.from(type);
        CriteriaQuery<ENTITY> select = criteriaQuery.select(from);
        TypedQuery<ENTITY> typedQuery = em.createQuery(select);
        return typedQuery.getResultList();
    }

    public ENTITY create(ENTITY entity) {
        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
            em.clear();
            return entity;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            em.clear();
            throw ex;
        }
    }

    public void deleteById(Integer id) {
        findById(id).ifPresent(p -> {
            try {
                em.persist(p);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
            em.clear();
        });
    }

    public ENTITY update(Integer id, ENTITY update) {
        if (findById(id).isPresent()) {
            em.getTransaction().begin();
            try {
                em.merge(update);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
            em.clear();
            return update;
        }
        return null;
    }

    public CriteriaQuery<Long> createCountQuery() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(type)));
        em.createQuery(countQuery);

        return countQuery;
    }

    public Root<ENTITY> getEntityRoot() {
        CriteriaQuery<ENTITY> criteriaQuery = criteriaBuilder.createQuery(type);
        return criteriaQuery.from(type);
    }
}
