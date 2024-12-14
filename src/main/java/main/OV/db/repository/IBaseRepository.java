package main.OV.db.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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