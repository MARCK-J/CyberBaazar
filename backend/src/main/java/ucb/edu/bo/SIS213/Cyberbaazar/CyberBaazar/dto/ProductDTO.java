package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    // Constructores, getters y setters
    public ProductDTO() {
    }

    public ProductDTO(Long productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
