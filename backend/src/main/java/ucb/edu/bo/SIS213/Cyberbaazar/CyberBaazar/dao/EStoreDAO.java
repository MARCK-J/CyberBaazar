package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.EStoreDTO;
import java.util.Optional;

public interface EStoreDAO extends JpaRepository<EStoreDTO, Long> {
    @Query("SELECT e FROM EStoreDTO e WHERE e.name = :name")
    Optional<EStoreDTO> findByName(@Param("name") String name);
}

