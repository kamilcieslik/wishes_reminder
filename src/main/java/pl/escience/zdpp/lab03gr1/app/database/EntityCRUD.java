package pl.escience.zdpp.lab03gr1.app.database;

import java.util.List;

public interface EntityCRUD<T> {
    List<T> getEntities();

    void saveEntity(T entity) throws Exception;

    T getEntity(int id);

    void deleteEntity(int id) throws Exception;
}
