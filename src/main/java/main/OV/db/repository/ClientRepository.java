package main.OV.db.repository;

import main.OV.db.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    /**
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    <S extends ClientEntity> S save(S entity);

    List<ClientEntity> findAll();

    Optional<ClientEntity> findByEmail(String email);
}
