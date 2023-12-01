package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Job")
public class JobDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Long jobId;

    @Column(name = "Job_id", length = 50, nullable = false)
    private String jobIdValue;

    @Column(name = "Products_id_product", nullable = false)
    private Long productId;

    @Column(name = "EStores_id_estores", nullable = false)
    private Long eStoreId;

    @Column(name = "Country_id_country", nullable = false)
    private Long countryId;

    // Constructores, getters y setters
    public JobDTO() {
    }

    public JobDTO(Long jobId, String jobIdValue, Long productId, Long eStoreId, Long countryId) {
        this.jobId = jobId;
        this.jobIdValue = jobIdValue;
        this.productId = productId;
        this.eStoreId = eStoreId;
        this.countryId = countryId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobIdValue() {
        return jobIdValue;
    }

    public void setJobIdValue(String jobIdValue) {
        this.jobIdValue = jobIdValue;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getEStoreId() {
        return eStoreId;
    }

    public void setEStoreId(Long eStoreId) {
        this.eStoreId = eStoreId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
