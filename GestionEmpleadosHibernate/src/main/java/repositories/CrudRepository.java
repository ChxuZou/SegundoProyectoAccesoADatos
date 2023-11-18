package repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, Integer> {
	List<T> findAll();

    Optional<T> findById(Integer id);

    boolean save(T entity);

    Boolean delete(T entity);
    
    boolean update(T entity);

}
