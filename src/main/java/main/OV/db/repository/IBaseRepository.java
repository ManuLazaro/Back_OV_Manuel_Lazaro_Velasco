package main.OV.db.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	List<T> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll(
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	Page<T> findAll(Pageable pageable);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */

	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR') || hasRole('ROLE_PROPERTY_USER') || hasRole('ROLE_MAINTAINER') || hasRole('ROLE_COUNCIL_USER')")
	@RestResource(exported = false)
	T findOne(@Param("id") ID id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.query.QueryByExampleExecutor#findOne(
	 * org.springframework.data.domain.Example)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	<S extends T> Optional<S> findOne(Example<S> example);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	<S extends T> S save(S entity);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.jpa.repository.JpaRepository#save(java.lang.
	 * Iterable)
	 */

	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	<S extends T> List<S> save(Iterable<S> arg0);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.
	 * Object)
	 */
	@Override
//	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	void delete(T entity);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#delete(java.io.
	 * Serializable)
	 */
	//@Override
//	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	void delete(ID id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.
	 * Iterable)
	 */
	//@Override
//	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	void delete(Iterable<? extends T> entities);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll(org.
	 * springframework.data.domain.Example,
	 * org.springframework.data.domain.Sort)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	<S extends T> List<S> findAll(Example<S> var1, Sort var2);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.
	 * springframework.data.domain.Example)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	@RestResource(exported = false)
	<S extends T> List<S> findAll(Example<S> example);

}