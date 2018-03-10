package pl.escience.zdpp.lab03gr1.app.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.app.database.entity.PersonAnniversary;
import pl.escience.zdpp.lab03gr1.app.database.entity.Relation;

import java.util.List;

public class PersonAnniversaryDAO {
    private final SessionFactory sessionFactory;

    public PersonAnniversaryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PersonAnniversary> getEntities() {
        List<PersonAnniversary> personAnniversaries;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<PersonAnniversary> theQuery = currentSession.createQuery("from person_anniversary", PersonAnniversary.class);
            personAnniversaries = theQuery.getResultList();
            currentSession.getTransaction().commit();
        }
        return personAnniversaries;
    }

    public void saveEntity(PersonAnniversary entity)  {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();

    }

    public PersonAnniversary getEntity(int id) {
        PersonAnniversary relation;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            relation = currentSession.get(PersonAnniversary.class, id);
            currentSession.getTransaction().commit();
        }
        return relation;
    }

    public void deleteEntity(int id) {
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from person_anniversary where id=:personAnniversaryId")
                    .setParameter("personAnniversaryId", id).executeUpdate();
            currentSession.getTransaction().commit();
        }
    }
}
