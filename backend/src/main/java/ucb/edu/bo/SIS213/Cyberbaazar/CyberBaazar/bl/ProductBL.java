package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.ProductDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBL {
    private ProductDAO productDAO;

    @Autowired
    public ProductBL(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductDTO> findAllProducts() {
        try {
            return productDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los productos");
        }
    }

    public ProductDTO findProductById(Long id) {
        try {
            Optional<ProductDTO> productDTO = productDAO.findById(id);
            if (productDTO.isPresent()) {
                return productDTO.get();
            } else {
                throw new RuntimeException("Producto no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        try {
            return productDAO.save(productDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el producto XD");
        }
    }

    public ProductDTO deleteProductById(Long id) {
        ProductDTO productDTO = findProductById(id);
        if (productDTO != null) {
            productDAO.deleteById(id);
        }
        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        try {
            return productDAO.save(productDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto");
        }
    }

    public Long findProductByName(String productName) {
        try {
            Optional<ProductDTO> productDTO = productDAO.findByName(productName);
            if (productDTO.isPresent()) {
                return productDTO.get().getProductId();
            } else {
                throw new RuntimeException("Producto no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Long findProductByNameOrCreate(String productName) {
        try {
            Optional<ProductDTO> productDTO = productDAO.findByName(productName);
            if (productDTO.isPresent()) {
                return productDTO.get().getProductId();
            } else {
                // Si el producto no existe, crea uno nuevo
                ProductDTO newProduct = new ProductDTO();
                newProduct.setName(productName);
                ProductDTO savedProduct = saveProduct(newProduct);
                return savedProduct.getProductId();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


