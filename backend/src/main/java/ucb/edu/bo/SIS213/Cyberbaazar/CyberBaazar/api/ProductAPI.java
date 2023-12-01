package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl.JobBL;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl.ProductBL;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ProductDTO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ResponseDTO;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/products")
public class ProductAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductAPI.class);

    @Autowired
    private ProductBL productBL;
    private JobBL jobBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllProducts() {
        try {
            LOGGER.info("Obteniendo lista de productos");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de productos");
            response.setData(productBL.findAllProducts());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al listar los productos", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar los productos");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findProductById(@PathVariable Long id) {
        try {
            LOGGER.info("Buscando producto con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Producto encontrado");
            response.setData(productBL.findProductById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al encontrar el producto", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar el producto");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
        try {
            LOGGER.info("Guardando producto");
            ProductDTO savedProduct = productBL.saveProduct(productDTO);

            // Enviar datos a la API externa
            jobBL.sendDataToPriceAnalytics(null, null, savedProduct.getName());
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Producto guardado");
            response.setData(productBL.saveProduct(productDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al guardar el producto", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al guardar el producto 2");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteProductById(@PathVariable Long id) {
        try {
            LOGGER.info("Eliminando producto con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Producto eliminado");
            response.setData(productBL.deleteProductById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el producto", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el producto");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
        try {
            LOGGER.info("Actualizando producto");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Producto actualizado");
            response.setData(productBL.updateProduct(productDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el producto", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el producto");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
