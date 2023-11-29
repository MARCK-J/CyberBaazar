package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.CountryDTO;

public interface CountryDAO extends JpaRepository<CountryDTO, Integer> {
    
}
