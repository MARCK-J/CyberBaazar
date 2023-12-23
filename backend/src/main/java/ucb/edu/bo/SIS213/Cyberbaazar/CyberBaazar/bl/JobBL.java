package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.JobDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.EStoreDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.JobDTO;

@Service
public class JobBL {

    private JobDAO jobDAO;
    private EStoreBL eStoreBL;
    private ProductBL productBL;
    private CountryBL countryBL;
    private PriceBL priceBL;
    private RestTemplate restTemplate;

    @Autowired
    public JobBL(JobDAO jobDAO, EStoreBL eStoreBL, ProductBL productBL, CountryBL countryBL, PriceBL priceBL, RestTemplate restTemplate) {
        this.jobDAO = jobDAO;
        this.eStoreBL = eStoreBL;
        this.productBL = productBL;
        this.countryBL = countryBL;
        this.priceBL = priceBL;
        this.restTemplate = restTemplate;
    }

    public List<JobDTO> findAllJobs() {
        try {
            return jobDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los trabajos 2");
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

    public ResponseEntity<JsonNode> callExternalApi(String source, String country, String values) {
        try {
            String apiUrl = "https://price-analytics.p.rapidapi.com/search-by-term";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("X-RapidAPI-Key", "1320b9c33bmsh5b861219bac6570p16aba6jsn9813b2764b31");
            headers.set("X-RapidAPI-Host", "price-analytics.p.rapidapi.com");

            // Buscar el producto por nombre o crear uno nuevo
            Long productId = productBL.findProductByNameOrCreate(values);

            String requestBody = "source=" + source + "&country=" + country + "&values=" + values;

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            //ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

            // Parsear la respuesta a un objeto JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(responseEntity.getBody());

            // Extraer el valor de job_id del JSON
            String jobIdVal = responseBody.get("job_id").asText();
            System.out.println("source: " + source);

            EStoreDTO eStoreDTO = eStoreBL.findEStoreByName(source);
            Long estoresId = eStoreDTO.getStoreId();
            Long countryId = countryBL.findCountryByCode(country);
            //Long productId = productBL.findProductByName(values);

            // Guardar el jobId en la base de datos
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobId(jobIdVal);
            jobDTO.setEstoresIdEstores(estoresId);
            jobDTO.setProductsIdProduct(productId);
            jobDTO.setCountryIdCountry(countryId);
            
            // Guardar en la base de datos
            jobDAO.save(jobDTO);

            priceBL.processExternalApiResponse(jobIdVal, source, country, values);

            return ResponseEntity.status(responseEntity.getStatusCode())
                    .headers(responseEntity.getHeaders())
                    .body(responseBody);
        } catch (Exception e) {
            // Manejar excepciones apropiadamente
            e.printStackTrace();
            throw new RuntimeException("Error al llamar a la API externa", e);
        }
    }
}
