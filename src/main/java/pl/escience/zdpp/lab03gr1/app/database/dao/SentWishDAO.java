package pl.escience.zdpp.lab03gr1.app.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.app.database.entity.Address;
import pl.escience.zdpp.lab03gr1.app.database.entity.SentWish;

import java.util.List;

public class SentWishDAO {
    private final SessionFactory sessionFactory;

    public SentWishDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<SentWish> getEntities() {
        List<SentWish> sentWishes;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<SentWish> theQuery = currentSession.createQuery("from sent_wish", SentWish.class);
            sentWishes = theQuery.getResultList();
            currentSession.getTransaction().commit();
        }
        return sentWishes;
    }

    public void saveEntity(SentWish entity)  {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();

    }

    public SentWish getEntity(int id) {
        SentWish sentWish;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            sentWish = currentSession.get(SentWish.class, id);
            currentSession.getTransaction().commit();
        }
        return sentWish;
    }

    public void deleteEntity(int id) {
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from sent_wish where id=:sentWishId")
                    .setParameter("sentWishId", id).executeUpdate();
            currentSession.getTransaction().commit();
        }
    }
}
