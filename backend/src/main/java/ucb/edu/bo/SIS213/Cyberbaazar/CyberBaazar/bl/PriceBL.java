package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.PriceDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.EStoreDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.PriceDTO;

@Service
public class PriceBL {

    private PriceDAO priceDAO;
    private EStoreBL eStoreBL;
    private ProductBL productBL;
    private CountryBL countryBL;
    private RestTemplate restTemplate;

    @Autowired
    public PriceBL(PriceDAO pricesDAO, EStoreBL eStoreBL, ProductBL productBL, CountryBL countryBL, RestTemplate restTemplate) {
        this.priceDAO = pricesDAO;
        this.eStoreBL = eStoreBL;
        this.productBL = productBL;
        this.countryBL = countryBL;
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
            secondHeaders.set("X-RapidAPI-Key", "7f26c57ed7msh1b75ef0bb587240p1e019djsn66f587f31b02");
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
                Thread.sleep(1000); // Esperar 1 segundo antes de la siguiente verificación
                secondResponseEntity = restTemplate.exchange(
                    secondApiUrl,
                    HttpMethod.GET,
                    new org.springframework.http.HttpEntity<>(secondHeaders),
                    String.class
                );
                secondResponseBody = objectMapper.readTree(secondResponseEntity.getBody());
            }

            // Puedes hacer algo con la respuesta del segundo endpoint, como imprimir en la consola
            System.out.println(secondResponseBody);

            // Guardar la información en la base de datos de precios
            /*PriceDTO priceDTO = new PriceDTO();
            priceDTO.setPriceId(jobIdVal);
            priceDTO.setImage("");  // Puedes establecer un valor real para la imagen
            priceDTO.setCode("");   // Puedes establecer un valor real para el código
            priceDTO.setName("");   // Puedes establecer un valor real para el nombre
            priceDTO.setUrlPrice("");  // Puedes establecer un valor real para la URL
            priceDTO.setDate(new Timestamp(System.currentTimeMillis()));
            // Asigna los valores adecuados para jobId, estoresId, countryId y productId
            priceDTO.setJobId(1L);  // Reemplaza 1L con el valor correcto de jobId
            priceDTO.setEstoresId(2L);  // Reemplaza 2L con el valor correcto de estoresId
            priceDTO.setCountryId(3L);  // Reemplaza 3L con el valor correcto de countryId
            priceDTO.setProductId(4L);  // Reemplaza 4L con el valor correcto de productId

            // Guardar en la base de datos de precios
            savePrice(priceDTO);*/

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al procesar la respuesta del segundo endpoint", e);
        }
    }
}
