package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl.CountryBL;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl.JobBL;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.CountryDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ResponseDTO;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/countries")
public class CountryAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryAPI.class);

    @Autowired
    private CountryBL countryBL;
    private JobBL jobBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllCountries() {
        try {
            LOGGER.info("Obteniendo lista de países");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de países");
            response.setData(countryBL.findAllCountries());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al listar los países", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar los países");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findCountryById(@PathVariable Long id) {
        try {
            LOGGER.info("Buscando país con ID: {}", id);
            // Obtener el país
            CountryDTO foundCountry = countryBL.findCountryById(id);

            // Enviar datos a la API externa utilizando IntegrationService
            jobBL.sendDataToPriceAnalytics(null, foundCountry.getCode(), null);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("País encontrado");
            response.setData(countryBL.findCountryById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al encontrar el país", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar el país");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveCountry(@Valid @RequestBody CountryDTO countryDTO, BindingResult result) {
        try {
            LOGGER.info("Guardando país");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("País guardado");
            response.setData(countryBL.saveCountry(countryDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al guardar el país", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al guardar el país");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCountryById(@PathVariable Long id) {
        try {
            LOGGER.info("Eliminando país con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("País eliminado");
            response.setData(countryBL.deleteCountryById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el país", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el país");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateCountry(@Valid @RequestBody CountryDTO countryDTO, BindingResult result) {
        try {
            LOGGER.info("Actualizando país");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("País actualizado");
            response.setData(countryBL.updateCountry(countryDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el país", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el país");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
