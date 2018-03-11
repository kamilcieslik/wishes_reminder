package pl.escience.zdpp.lab03gr1.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.escience.zdpp.lab03gr1.database.EntityCRUD;
import pl.escience.zdpp.lab03gr1.database.entity.Relation;

import java.util.List;

public class RelationDAO implements EntityCRUD<Relation> {
    private final SessionFactory sessionFactory;

    public RelationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Relation> getEntities() {
        List<Relation> relations;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Query<Relation> theQuery = currentSession.createQuery("from Relation", Relation.class);
        relations = theQuery.getResultList();
        currentSession.getTransaction().commit();

        return relations;
    }

    public void saveEntity(Relation entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(entity);
        currentSession.getTransaction().commit();

    }

    public Relation getEntity(int id) {
        Relation relation;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        relation = currentSession.get(Relation.class, id);
        currentSession.getTransaction().commit();

        return relation;
    }

    public void deleteEntity(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createQuery("delete from Relation where id=:relationId")
                .setParameter("relationId", id).executeUpdate();
        currentSession.getTransaction().commit();
    }
}
