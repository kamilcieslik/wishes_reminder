package pl.escience.zdpp.lab03gr1.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.database.EntityCRUD;
import pl.escience.zdpp.lab03gr1.database.entity.PersonAnniversary;

import java.util.List;

public class PersonAnniversaryDAO implements EntityCRUD<PersonAnniversary> {
    private final SessionFactory sessionFactory;

    public PersonAnniversaryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PersonAnniversary> getEntities() {
        List<PersonAnniversary> personAnniversaries;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Query<PersonAnniversary> theQuery = currentSession.createQuery("from PersonAnniversary", PersonAnniversary.class);
        personAnniversaries = theQuery.getResultList();
        currentSession.getTransaction().commit();

        return personAnniversaries;
    }

    public void saveEntity(PersonAnniversary entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();
    }

    public PersonAnniversary getEntity(int id) {
        PersonAnniversary personAnniversary;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        personAnniversary = currentSession.get(PersonAnniversary.class, id);
        currentSession.getTransaction().commit();

        return personAnniversary;
    }

    public void deleteEntity(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createQuery("delete from PersonAnniversary where id=:personAnniversaryId")
                .setParameter("personAnniversaryId", id).executeUpdate();
        currentSession.getTransaction().commit();
    }
}
