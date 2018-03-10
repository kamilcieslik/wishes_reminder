package pl.escience.zdpp.lab03gr1.app.database.dao;

import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.app.database.entity.Address;
import pl.escience.zdpp.lab03gr1.app.database.entity.Relation;

import java.util.List;

public class RelationDAO {
    private final SessionFactory sessionFactory;

    public RelationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Relation> getEntities() {
        List<Relation> relations;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Relation> theQuery = currentSession.createQuery("from relation", Relation.class);
            relations = theQuery.getResultList();
            currentSession.getTransaction().commit();
        }
        return relations;
    }

    public void saveEntity(Relation entity)  {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();

    }

    public Relation getEntity(int id) {
        Relation relation;
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            relation = currentSession.get(Relation.class, id);
            currentSession.getTransaction().commit();
        }
        return relation;
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
