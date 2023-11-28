package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<UsuarioDTO, Integer> {
    
}
