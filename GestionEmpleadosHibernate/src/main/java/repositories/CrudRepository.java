package repositories;

import java.util.List;
import java.util.Optional;

/**
 * El método save( ) sirve tanto para guardar como para actualizar
 * @param <T>
 * @param <Integer>
 */
public interface CrudRepository<T, Integer> {
	List<T> findAll();

    Optional<T> findById(Integer id);

    boolean save(T entity);

    Boolean delete(T entity);
    
}
