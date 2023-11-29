package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ConsultasGptDTO;

public interface ConsultasGptDAO extends JpaRepository<ConsultasGptDTO, Integer> {
    // Puedes añadir consultas personalizadas si es necesario
}