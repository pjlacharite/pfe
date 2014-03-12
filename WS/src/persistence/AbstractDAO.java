package persistence;

import java.util.List;

public abstract class AbstractDAO<T> {

    public abstract T create(T object);

    public abstract T find(String id);

    public abstract List<T> findAll(List<String> filters);
}
