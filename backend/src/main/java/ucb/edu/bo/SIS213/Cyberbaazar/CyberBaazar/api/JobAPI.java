package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl.JobBL;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ExternalApiDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.JobDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ResponseDTO;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/jobs")
public class JobAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobAPI.class);

    @Autowired
    private JobBL jobBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllJobs() {
        try {
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de trabajos");
            response.setData(jobBL.findAllJobs());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return handleErrorResponse(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findJobById(@PathVariable Long id) {
        try {
            LOGGER.info("Buscando trabajo con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Trabajo encontrado");
            response.setData(jobBL.findJobById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al encontrar el trabajo", e);
            return handleErrorResponse(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveJob(@Valid @RequestBody JobDTO jobDTO) {
        try {
            LOGGER.info("Guardando trabajo");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Trabajo guardado");
            response.setData(jobBL.saveJob(jobDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al guardar el trabajo", e);
            return handleErrorResponse(e);
        }
    }

    @PostMapping("/call-external-api")
    public ResponseEntity<ResponseDTO> callExternalApi(@RequestBody ExternalApiDTO externalApiDTO) {
        try {
            LOGGER.info("Llamando a la API externa");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Llamada a la API externa exitosa");
            response.setData(jobBL.callExternalApi(externalApiDTO.getSource(), externalApiDTO.getCountry(), externalApiDTO.getValues()).getBody());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al llamar a la API externa", e);
            return handleErrorResponse(e);
        }
    }

    private ResponseEntity<ResponseDTO> handleErrorResponse(Exception e) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(500);
        response.setMessage("Error en la solicitud");
        response.setError("Error al llamar a la API externa");
        response.setData(null);
        return ResponseEntity.status(500).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteJobById(@PathVariable Long id) {
        try {
            LOGGER.info("Eliminando trabajo con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Trabajo eliminado");
            response.setData(jobBL.deleteJobById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el trabajo", e);
            return handleErrorResponse(e);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateJob(@Valid @RequestBody JobDTO jobDTO) {
        try {
            LOGGER.info("Actualizando trabajo");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Trabajo actualizado");
            response.setData(jobBL.updateJob(jobDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el trabajo", e);
            return handleErrorResponse(e);
        }
    }
}

