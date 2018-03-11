package pl.escience.zdpp.lab03gr1.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.database.EntityCRUD;
import pl.escience.zdpp.lab03gr1.database.entity.User;
import pl.escience.zdpp.lab03gr1.database.exception.UniqueViolationException;

import javax.persistence.NoResultException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class UserDAO implements EntityCRUD<User> {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getEntities() {
        List<User> users;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Query<User> theQuery = currentSession.createQuery("from User", User.class);
        users = theQuery.getResultList();
        currentSession.getTransaction().commit();

        return users;
    }

    public void saveEntity(User entity) throws UniqueViolationException {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
        try {
            currentSession.saveOrUpdate(entity);
            currentSession.getTransaction().commit();
        }
        catch (ConstraintViolationException e){
            Throwable eCause = e.getCause();
            currentSession.close();
            while ((eCause != null) && !(eCause instanceof SQLIntegrityConstraintViolationException))
                eCause = eCause.getCause();
            if (eCause != null) {
                Throwable exceptionCause;
                exceptionCause = new Throwable("podany login już istnieje");
                throw new UniqueViolationException("Błąd bazy danych", exceptionCause);
            }
        }
    }

    public User getEntity(int id) {
        User user;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        user = currentSession.get(User.class, id);
        currentSession.getTransaction().commit();

        return user;
    }

    public User getEntityByLogin(String login) {
        User user;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        try {
            user = currentSession.createQuery("from User where login=:userLogin", User.class)
                    .setParameter("userLogin", login).getSingleResult();
            currentSession.getTransaction().commit();
        } catch (NoResultException e) {
            currentSession.close();
            throw e;
        }
        return user;
    }

    public void deleteEntity(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createQuery("delete from User where id=:userId")
                .setParameter("userId", id).executeUpdate();
        currentSession.getTransaction().commit();
    }
}
