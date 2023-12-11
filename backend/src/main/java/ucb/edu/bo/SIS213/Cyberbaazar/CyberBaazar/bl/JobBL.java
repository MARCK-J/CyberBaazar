package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.JobDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.JobDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service
public class JobBL {

    private JobDAO jobDAO;
    private final RestTemplate restTemplate;

    @Autowired
    public JobBL(JobDAO jobDAO, RestTemplate restTemplate) {
        this.jobDAO = jobDAO;
        this.restTemplate = restTemplate;
    }

    public List<JobDTO> findAllJobs() {
        try {
            return jobDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los trabajos");
        }
    }

    public JobDTO findJobById(Long id) {
        try {
            Optional<JobDTO> jobDTO = jobDAO.findById(id);
            if (jobDTO.isPresent()) {
                return jobDTO.get();
            } else {
                throw new RuntimeException("Trabajo no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public JobDTO saveJob(JobDTO jobDTO) {
        try {
            return jobDAO.save(jobDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el trabajo");
        }
    }

    public JobDTO deleteJobById(Long id) {
        JobDTO jobDTO = findJobById(id);
        if (jobDTO != null) {
            jobDAO.deleteById(id);
        }
        return jobDTO;
    }

    public JobDTO updateJob(JobDTO jobDTO) {
        try {
            return jobDAO.save(jobDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el trabajo");
        }
    }
}
