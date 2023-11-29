package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Job")
public class JobDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer jobId;

    @Column(name = "Job_id", length = 50, nullable = false)
    private String jobIdValue;

    @Column(name = "Products_id_product", nullable = false)
    private Integer productId;

    @Column(name = "EStores_id_estores", nullable = false)
    private Integer eStoreId;

    @Column(name = "Country_id_country", nullable = false)
    private Integer countryId;

    // Constructores, getters y setters
    public JobDTO() {
    }

    public JobDTO(Integer jobId, String jobIdValue, Integer productId, Integer eStoreId, Integer countryId) {
        this.jobId = jobId;
        this.jobIdValue = jobIdValue;
        this.productId = productId;
        this.eStoreId = eStoreId;
        this.countryId = countryId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobIdValue() {
        return jobIdValue;
    }

    public void setJobIdValue(String jobIdValue) {
        this.jobIdValue = jobIdValue;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getEStoreId() {
        return eStoreId;
    }

    public void setEStoreId(Integer eStoreId) {
        this.eStoreId = eStoreId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
}
