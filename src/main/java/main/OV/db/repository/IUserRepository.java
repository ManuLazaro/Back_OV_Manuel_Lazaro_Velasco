package main.OV.db.repository;

import main.OV.db.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * The Interface IUserRepository.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface IUserRepository extends IBaseRepository <UserEntity, Long> {

	/* (non-Javadoc)
	 * @see com.hiberus.avant.db.repository.IBaseRepository#findOne(java.io.Serializable)
	 */
	@Override
	@RestResource(exported = false)
	@PreAuthorize("isAuthenticated()")
	UserEntity findOne(@Param("id") Long id);

	@Override
	default <S extends UserEntity> Optional<S> findOne(Example<S> example) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hiberus.avant.db.repository.IBaseRepository#findOne(java.io.Serializable)
	 */
	@Query(" from AvtUserEntity u where u.id = :id ")
	@RestResource(exported = false)
	UserEntity findOneCredentials(@Param("id") Long id);

	@Override
	default <S extends UserEntity> List<S> saveAll(Iterable<S> entities) {
		return Collections.emptyList();
	}

	@Override
	default Optional<UserEntity> findById(Long aLong) {
		return Optional.empty();
	}

	@Override
	default boolean existsById(Long aLong) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hiberus.avant.db.repository.IBaseRepository#findAll()
	 */
	@Override
	@PreAuthorize("isAuthenticated()")
	List<UserEntity> findAll();

	@Override
	default List<UserEntity> findAllById(Iterable<Long> longs) {
		return Collections.emptyList();
	}

	@Override
	default long count() {
		return 0;
	}

	@Override
	default void deleteById(Long aLong) {

	}

	/* (non-Javadoc)
	 * @see com.hiberus.avant.db.repository.IBaseRepository#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	@PreAuthorize("isAuthenticated()")
	Page<UserEntity> findAll(Pageable pageable);
	
	/**
	 * get user whose id is the of authenticated user id.
	 *
	 * @return the user
	 */
	@Query("from AvtUserEntity u where u.id = ?#{ principal?.id }")
	@RestResource(path = "user", rel = "user")
	@PreAuthorize("isAuthenticated()")
	UserEntity getUser();

	/**
	 * get user whose id is the of authenticated user id.
	 *
	 * @param email the email
	 * @return the user by email
	 */
	@Query("from AvtUserEntity u where u.email = :email")
	@RestResource (exported = true)
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR') || hasRole('ROLE_COUNCIL_USER')")
	UserEntity getUserByEmail(@Param("email") String email);


	/* (non-Javadoc)
	 * @see com.hiberus.avant.impl.db.repository.IBaseRepository#save(java.lang.Object)
	 */
	@Override
	@RestResource(exported = false)
	@Secured("permitAll")
	public <S extends UserEntity> S save(S entity);

	@Override
	default <S extends UserEntity> List<S> save(Iterable<S> arg0) {
		return Collections.emptyList();
	}

	@Override
	default void delete(UserEntity entity) {

	}

	@Override
	default void deleteAllById(Iterable<? extends Long> longs) {

	}

	@Override
	default void deleteAll(Iterable<? extends UserEntity> entities) {

	}

	@Override
	default void deleteAll() {

	}

	@Override
	default void delete(Long aLong) {

	}

	@Override
	default void delete(Iterable<? extends UserEntity> entities) {

	}

	@Override
	default <S extends UserEntity> List<S> findAll(Example<S> var1, Sort var2) {
		return Collections.emptyList();
	}

	@Override
	default <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	default <S extends UserEntity> long count(Example<S> example) {
		return 0;
	}

	@Override
	default <S extends UserEntity> boolean exists(Example<S> example) {
		return false;
	}

	@Override
	default <S extends UserEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
		return null;
	}

	@Override
	default void flush() {

	}

	@Override
	default <S extends UserEntity> S saveAndFlush(S entity) {
		return null;
	}

	@Override
	default <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
		return Collections.emptyList();
	}

	@Override
	default void deleteAllInBatch(Iterable<UserEntity> entities) {

	}

	@Override
	default void deleteAllByIdInBatch(Iterable<Long> longs) {

	}

	@Override
	default void deleteAllInBatch() {

	}

	@Override
	default UserEntity getOne(Long aLong) {
		return null;
	}

	@Override
	default UserEntity getById(Long aLong) {
		return null;
	}

	@Override
	default UserEntity getReferenceById(Long aLong) {
		return null;
	}

	@Override
	default <S extends UserEntity> List<S> findAll(Example<S> example) {
		return Collections.emptyList();
	}

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the avt user entity
	 */
	@RestResource(exported = false)
	@Secured("permitAll")
	UserEntity findByEmail(@Param("email") String email);

	@RestResource(exported = false)
	@Secured("permitAll")
	@Query(value="select user FROM AvtUserEntity user " +
			" left join fetch user.buildings"
			+ " WHERE email = :email")
	UserEntity findByEmailCron(@Param("email") String email);
	/**
	 * Find the user of an administrator.
	 *
	 * @param id the id
	 * @return the administrator users
	 */
	@RestResource(exported = true)
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR') || ((hasRole('ROLE_PROPERTY_USER') || hasRole('ROLE_COUNCIL_USER')) && @securityPreAuthorize.belongToAdministrator(principal, #id))")
	@Query(value="select user FROM AvtUserEntity user " +
			" JOIN user.administrators admins"
			+ " WHERE admins.id = :id")
	List<UserEntity> getAdministratorUsers(@Param("id") Long id);

	/**
	 * Find one by contract.
	 *
	 * @param contract the contract
	 * @return the avt user entity
	 */
	@RestResource(exported = false)
	@Query("SELECT user "
			+ "FROM AvtUserEntity user "
			+ "JOIN user.buildings buildings "
			+ "JOIN buildings.buildingServices buildingServices "
			+ "WHERE buildingServices.contract = :contract and buildingServices.endDate is null")
	public UserEntity findOneByContract(@Param("contract") String contract);
	
	
	/**
	 * Gets the users by role.
	 *
	 * @return the users by role
	 */
	@RestResource(exported = true)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR','ROLE_ENGINEER')")
	@Query(value="select user FROM AvtUserEntity user "
			+ "JOIN user.roles roles "
			+ "WHERE ((1 = ?#{(hasRole('ROLE_ADMIN') OR hasRole('ROLE_DISTRIBUTOR')) ? 1 : 0} AND user.delegation = ?#{principal.delegationId}) "
			+ "OR (1 = ?#{hasRole('ROLE_ENGINEER') ? 1 : 0}) AND user.id = ?#{principal.id} ) "
			+ "AND roles.id = com.hiberus.avant.util.Constants.ROLE_ENGINEER_ID")
	List<UserEntity> getEngineers();


	/**
	 *
	 * @param id
	 * @return
	 */
	@Query("from AvtUserEntity u where u.id = :id")
	@RestResource
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DISTRIBUTOR')")
	UserEntity getInstaller(@Param("id") Long id);

	@RestResource(exported = false)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR')")
	@Query(value="select case when (count(user) > 0) then true else false end FROM AvtUserEntity user "
			+ "JOIN user.roles roles "
			+ "WHERE user.email = :email AND roles.id = com.hiberus.avant.util.Constants.ROLE_ENGINEER_ID")
	Boolean checkEngineer(@Param("email") String email);

	@RestResource(exported = false)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR')")
	@Query(value="select user FROM AvtUserEntity user "
			+ "JOIN user.roles roles "
			+ "WHERE user.email = :email AND roles.id = com.hiberus.avant.util.Constants.ROLE_ENGINEER_ID")
	UserEntity findEngineerByEmail(@Param("email") String email);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISTRIBUTOR', 'ROLE_PROPERTY_USER')")
	@RestResource(exported = false)
	@Query("SELECT user "
			+ "FROM AvtUserEntity user "
			+ "JOIN user.buildings building "
			+ "WHERE building.id = :id")
	UserEntity findByBuilding(@Param("id") Long id);


	@RestResource(exported = false)
	@PreAuthorize("isAuthenticated()")
	@Modifying
	@Query("UPDATE AvtUserEntity user SET user.nif = :dni WHERE user.id = :id")
	void updateDniOwner(@Param("id") Long id, @Param("dni") String dni);


	@Override
	default List<UserEntity> findAll(Sort sort) {
		return Collections.emptyList();
	}
}
