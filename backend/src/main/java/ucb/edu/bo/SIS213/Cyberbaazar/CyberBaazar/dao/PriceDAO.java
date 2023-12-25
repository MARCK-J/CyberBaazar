package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.PriceDTO;

import java.util.List;

public interface PriceDAO extends JpaRepository<PriceDTO, Long> {
    List<PriceDTO> findPricesByJobId(String jobId);
}



