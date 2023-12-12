package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Job")
public class JobDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Long idJob;

    @Column(name = "job_id", nullable = false)
    private String jobId;

    @Column(name = "products_id_product", nullable = false)
    private Long productsIdProduct;

    @Column(name = "estores_id_estores", nullable = false)
    private Long estoresIdEstores;

    @Column(name = "country_id_country", nullable = false)
    private Long countryIdCountry;

    // generame el Constructor

    public JobDTO() {
    }
    
    public JobDTO(Long idJob, String jobId, Long productsIdProduct, Long estoresIdEstores, Long countryIdCountry) {
        this.idJob = idJob;
        this.jobId = jobId;
        this.productsIdProduct = productsIdProduct;
        this.estoresIdEstores = estoresIdEstores;
        this.countryIdCountry = countryIdCountry;
    }

    public Long getIdJob() {
        return idJob;
    }

    public void setIdJob(Long idJob) {
        this.idJob = idJob;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Long getProductsIdProduct() {
        return productsIdProduct;
    }

    public void setProductsIdProduct(Long productsIdProduct) {
        this.productsIdProduct = productsIdProduct;
    }

    public Long getEstoresIdEstores() {
        return estoresIdEstores;
    }

    public void setEstoresIdEstores(Long estoresIdEstores) {
        this.estoresIdEstores = estoresIdEstores;
    }

    public Long getCountryIdCountry() {
        return countryIdCountry;
    }

    public void setCountryIdCountry(Long countryIdCountry) {
        this.countryIdCountry = countryIdCountry;
    }
}
