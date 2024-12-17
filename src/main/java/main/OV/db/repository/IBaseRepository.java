package main.OV.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * The Interface IBaseRepository.
 *
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}