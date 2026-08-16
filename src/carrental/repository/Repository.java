package carrental.repository;

import java.util.List;

public interface Repository<T> {
    boolean add(T item);
    T findById(long id);
    List<T> findAll();
    boolean removeById(long id);
    int count();
}
