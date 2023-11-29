package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.JobDTO;

public interface JobDAO extends JpaRepository<JobDTO, Integer> {
    
}

