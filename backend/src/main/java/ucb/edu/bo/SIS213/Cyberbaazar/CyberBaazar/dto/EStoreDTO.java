package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "EStores")
public class EStoreDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estores")
    private Long storeId;

    @Column(name = "name")
    private String name;

    // Constructores, getters y setters
    public EStoreDTO() {
    }

    public EStoreDTO(Long storeId, String name) {
        this.storeId = storeId;
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


