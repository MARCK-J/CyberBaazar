package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ProductDTO;
import java.util.Optional;


public interface ProductDAO extends JpaRepository<ProductDTO, Long> {
    Optional<ProductDTO> findByName(String name);
}

