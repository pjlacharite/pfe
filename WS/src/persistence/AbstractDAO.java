package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> {

    Connection connection = null;

    public abstract T create(T object);

    public abstract T find(String id);

    public abstract List<T> findAll(List<String> filters);

    public void releaseConnection() throws SQLException{
        this.connection.close();
    }
}
