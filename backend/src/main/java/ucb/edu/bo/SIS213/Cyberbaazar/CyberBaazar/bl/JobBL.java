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

    public String sendDataToPriceAnalytics(String estoreName, String countryCode, String productName) {
        String apiUrl = "https://price-analytics.p.rapidapi.com/search-by-term";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-RapidAPI-Key", "7f26c57ed7msh1b75ef0bb587240p1e019djsn66f587f31b02");
        headers.set("X-RapidAPI-Host", "price-analytics.p.rapidapi.com");

        // Construir el cuerpo de la solicitud
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("source=").append(estoreName).append("&");
        requestBody.append("country=").append(countryCode).append("&");
        requestBody.append("values=").append(productName);

        // Realizar la llamada a la API externa con headers y obtener la respuesta
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, new HttpEntity<>(requestBody.toString(), headers), String.class);

        // Devolver la respuesta como String
        return response.getBody();
    }
}
