package pl.escience.zdpp.lab03gr1.app.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.app.database.entity.SentWish;
import pl.escience.zdpp.lab03gr1.app.database.entity.User;

import java.util.List;

public class UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getEntities() {
        List<User> users;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<User> theQuery = currentSession.createQuery("from user", User.class);
            users = theQuery.getResultList();
            currentSession.getTransaction().commit();
        }
        return users;
    }

    public void saveEntity(User entity)  {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();

    }

    public User getEntity(int id) {
        User user;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            user = currentSession.get(User.class, id);
            currentSession.getTransaction().commit();
        }
        return user;
    }

    public void deleteEntity(int id) {
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from user where id=:userId")
                    .setParameter("userId", id).executeUpdate();
            currentSession.getTransaction().commit();
        }
    }
}
