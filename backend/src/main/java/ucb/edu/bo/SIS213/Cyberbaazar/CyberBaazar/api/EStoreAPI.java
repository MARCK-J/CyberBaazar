package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl.EStoreBL;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.EStoreDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ResponseDTO;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/estores")
public class EStoreAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(EStoreAPI.class);

    @Autowired
    private EStoreBL eStoreBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllEStores() {
        try {
            LOGGER.info("Obteniendo lista de tiendas");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de tiendas");
            response.setData(eStoreBL.findAllEStores());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al listar las tiendas", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar las tiendas");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findEStoreById(@PathVariable Long id) {
        try {
            LOGGER.info("Buscando tienda con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Tienda encontrada");
            response.setData(eStoreBL.findEStoreById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al encontrar la tienda", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar la tienda");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveEStore(@Valid @RequestBody EStoreDTO eStoreDTO) {
        try {
            LOGGER.info("Guardando tienda");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Tienda guardada");
            response.setData(eStoreBL.saveEStore(eStoreDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al guardar la tienda", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al guardar la tienda");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteEStoreById(@PathVariable Long id) {
        try {
            LOGGER.info("Eliminando tienda con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Tienda eliminada");
            response.setData(eStoreBL.deleteEStoreById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar la tienda", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar la tienda");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateEStore(@Valid @RequestBody EStoreDTO eStoreDTO, BindingResult result) {
        try {
            LOGGER.info("Actualizando tienda");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Tienda actualizada");
            response.setData(eStoreBL.updateEStore(eStoreDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar la tienda", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar la tienda");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    
}
