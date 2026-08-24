package carrental.repository;

import java.util.List;

public interface Repository<T, ID> {
    boolean add(T item);
    T findById(ID id);
    List<T> findAll();
    boolean removeById(ID id);
    int count();
}
