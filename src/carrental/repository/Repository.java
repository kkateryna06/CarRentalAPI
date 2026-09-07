package carrental.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    boolean add(T item);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean removeById(ID id);
    int count();
}
