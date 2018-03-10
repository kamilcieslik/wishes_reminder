package pl.escience.zdpp.lab03gr1.app.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.app.database.entity.Address;

import java.util.List;

public class RelationDAO {
    private final SessionFactory sessionFactory;

    public RelationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Address> getEntities() {
        List<Address> addresses;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Address> theQuery = currentSession.createQuery("from relation", Address.class);
            addresses = theQuery.getResultList();
            currentSession.getTransaction().commit();
        }
        return addresses;
    }

    public void saveEntity(Address entity)  {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();

    }

    public Address getEntity(int id) {
        Address address;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            address = currentSession.get(Address.class, id);
            currentSession.getTransaction().commit();
        }
        return address;
    }

    public void deleteEntity(int id) {
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from relation where id=:relationId")
                    .setParameter("relationId", id).executeUpdate();
            currentSession.getTransaction().commit();
        }
    }
}
