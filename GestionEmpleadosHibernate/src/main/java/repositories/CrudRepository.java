package repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, Integer> {
	List<T> findAll();

    Optional<T> findById(Integer id);

    T save(T entity);

    Boolean delete(T entity);

}
