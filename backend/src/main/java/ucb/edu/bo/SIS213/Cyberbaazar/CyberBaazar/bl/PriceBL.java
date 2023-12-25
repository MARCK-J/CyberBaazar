package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.PriceDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.PriceDTO;

@Service
public class PriceBL {

    private PriceDAO priceDAO;
    private RestTemplate restTemplate;

    @Autowired
    public PriceBL(PriceDAO pricesDAO, EStoreBL eStoreBL, ProductBL productBL, CountryBL countryBL, RestTemplate restTemplate) {
        this.priceDAO = pricesDAO;
        this.restTemplate = restTemplate;
    }

    public List<PriceDTO> findAllPrices() {
        try {
            return priceDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los precios");
        }
    }

    public PriceDTO findPriceById(Long id) {
        try {
            Optional<PriceDTO> priceDTO = priceDAO.findById(id);
            if (priceDTO.isPresent()) {
                return priceDTO.get();
            } else {
                throw new RuntimeException("Precio no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PriceDTO savePrice(PriceDTO priceDTO) {
        try {
            return priceDAO.save(priceDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el precio");
        }
    }

    public PriceDTO deletePriceById(Long id) {
        PriceDTO priceDTO = findPriceById(id);
        if (priceDTO != null) {
            priceDAO.deleteById(id);
        }
        return priceDTO;
    }

    public PriceDTO updatePrice(PriceDTO priceDTO) {
        try {
            return priceDAO.save(priceDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el precio");
        }
    }

    public void processExternalApiResponse(String jobIdVal, String source, String country, String values) {
        try {
            // Llamada al segundo endpoint para obtener los resultados
            String secondApiUrl = "https://price-analytics.p.rapidapi.com/poll-job/" + jobIdVal;

            HttpHeaders secondHeaders = new HttpHeaders();
            secondHeaders.set("X-RapidAPI-Key", "1320b9c33bmsh5b861219bac6570p16aba6jsn9813b2764b31");
            secondHeaders.set("X-RapidAPI-Host", "price-analytics.p.rapidapi.com");

            ResponseEntity<String> secondResponseEntity = restTemplate.exchange(
                secondApiUrl,
                HttpMethod.GET,
                new org.springframework.http.HttpEntity<>(secondHeaders),
                String.class
            );

            // Parsear la respuesta del segundo endpoint
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode secondResponseBody = objectMapper.readTree(secondResponseEntity.getBody());

            // Esperar hasta que el estado sea "finished"
            while (!"finished".equalsIgnoreCase(secondResponseBody.get("status").asText())) {
                Thread.sleep(10000); // Esperar 1 segundo antes de la siguiente verificación
                secondResponseEntity = restTemplate.exchange(
                    secondApiUrl,
                    HttpMethod.GET,
                    new org.springframework.http.HttpEntity<>(secondHeaders),
                    String.class
                );
                secondResponseBody = objectMapper.readTree(secondResponseEntity.getBody());
            }

            // Puedes hacer algo con la respuesta del segundo endpoint, como imprimir en la consola
            // Accede a la información completa desde "job_id" hasta "offers"
            String jobId = secondResponseBody.get("job_id").asText();
            String updatedAt = secondResponseBody.path("results").get(0).path("updated_at").asText();

            System.out.println("jobId: " + jobId);
            System.out.println("updatedAt: " + updatedAt);

            // Guardar la información en la base de datos de precios
            JsonNode offers = secondResponseBody.path("results").get(0).path("content").path("offers");
            //System.out.println("Second API Response: " + offers);
            for (JsonNode offer : offers) {
                PriceDTO priceDTO = new PriceDTO();
                priceDTO.setImage(offer.path("image").asText());
                priceDTO.setPrice(offer.path("price").asText());
                priceDTO.setName(offer.path("name").asText());
                priceDTO.setUrlPrice(offer.path("link").path("href").asText());

                // Formatear la cadena de fecha y hora al formato deseado
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date parsedDate = dateFormat.parse(updatedAt);

                // Crear un objeto Timestamp a partir de la fecha parseada
                Timestamp timestamp = new Timestamp(parsedDate.getTime());

                priceDTO.setDate(timestamp);
                priceDTO.setJobId(jobId);
                //priceDTO.setDesiredProductId(/* Reemplaza con el valor correcto de desiredProductId );
                savePrice(priceDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al procesar la respuesta del segundo endpoint", e);
        }
    }
}
