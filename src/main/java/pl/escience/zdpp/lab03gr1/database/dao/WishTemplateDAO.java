package pl.escience.zdpp.lab03gr1.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.database.EntityCRUD;
import pl.escience.zdpp.lab03gr1.database.entity.WishTemplate;

import java.util.List;

public class WishTemplateDAO implements EntityCRUD<WishTemplate> {
    private final SessionFactory sessionFactory;

    public WishTemplateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<WishTemplate> getEntities() {
        List<WishTemplate> wishTemplates;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Query<WishTemplate> theQuery = currentSession.createQuery("from WishTemplate", WishTemplate.class);
        wishTemplates = theQuery.getResultList();
        currentSession.getTransaction().commit();

        return wishTemplates;
    }

    public void saveEntity(WishTemplate entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();
    }

    public WishTemplate getEntity(int id) {
        WishTemplate wishTemplate;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        wishTemplate = currentSession.get(WishTemplate.class, id);
        currentSession.getTransaction().commit();

        return wishTemplate;
    }

    public void deleteEntity(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createQuery("delete from WishTemplate where id=:wishTemplateId")
                .setParameter("wishTemplateId", id).executeUpdate();
        currentSession.getTransaction().commit();
    }
}
