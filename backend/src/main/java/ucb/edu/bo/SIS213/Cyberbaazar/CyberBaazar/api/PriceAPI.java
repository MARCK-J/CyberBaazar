package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl.PriceBL;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.PriceDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ResponseDTO;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/prices")
public class PriceAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceAPI.class);

    @Autowired
    private PriceBL priceBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllPrices() {
        try {
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de precios");
            response.setData(priceBL.findAllPrices());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return handleErrorResponse(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findPriceById(@PathVariable Long id) {
        try {
            LOGGER.info("Buscando precio con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Precio encontrado");
            response.setData(priceBL.findPriceById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al encontrar el precio", e);
            return handleErrorResponse(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> savePrice(@Valid @RequestBody PriceDTO priceDTO) {
        try {
            LOGGER.info("Guardando precio");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Precio guardado");
            response.setData(priceBL.savePrice(priceDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al guardar el precio", e);
            return handleErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePriceById(@PathVariable Long id) {
        try {
            LOGGER.info("Eliminando precio con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Precio eliminado");
            response.setData(priceBL.deletePriceById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el precio", e);
            return handleErrorResponse(e);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updatePrice(@Valid @RequestBody PriceDTO priceDTO) {
        try {
            LOGGER.info("Actualizando precio");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Precio actualizado");
            response.setData(priceBL.updatePrice(priceDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el precio", e);
            return handleErrorResponse(e);
        }
    }

    @GetMapping("/by-job-id/{jobId}")
    public ResponseEntity<ResponseDTO> findPricesByJobId(@PathVariable String jobId) {
        try {
            LOGGER.info("Buscando precios por jobId: {}", jobId);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Precios encontrados por jobId");
            response.setData(priceBL.findPricesByJobId(jobId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al encontrar los precios por jobId", e);
            return handleErrorResponse(e);
        }
    }

    private ResponseEntity<ResponseDTO> handleErrorResponse(Exception e) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(500);
        response.setMessage("Error en la solicitud");
        response.setError(e.getMessage());
        response.setData(null);
        return ResponseEntity.status(500).body(response);
    }
}
