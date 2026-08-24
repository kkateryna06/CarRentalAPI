package carrental.repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryUtils {
    public static <T, ID> int addAll(Repository<T, ID> repository, List<? extends T> items) {
        if (repository == null) throw new IllegalArgumentException("Repository can't be null");
        if (items == null) throw new IllegalArgumentException("Items can't be null");

        for (T item : items) {
            if (item == null) throw new IllegalArgumentException("Items can't contain null");
        }

        int count = 0;
        for (T item : items) {
            if (repository.add(item)) count++;
        }

        return count;
    }

    public static <T, ID> List<T> findAllByIds(Repository<T, ID> repository, List<? extends ID> ids) {
        if (repository == null) throw new IllegalArgumentException("Repository can't be null");
        if (ids == null) throw new IllegalArgumentException("Ids can't be null");

        for (ID id : ids) {
            if (id == null) throw new IllegalArgumentException("Ids can't contain null");
        }

        List<T> resultList = new ArrayList<>();
        for (ID id : ids) {
            resultList.add(repository.findById(id));

        }

        return resultList;
    }
}
