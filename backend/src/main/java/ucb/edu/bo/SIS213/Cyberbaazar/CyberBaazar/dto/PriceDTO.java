package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Prices")
public class PriceDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_price")
    private Long priceId;

    @Column(name = "image", length = 100, nullable = false)
    private String image;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "url_price",length = 1000, nullable = false)
    private String urlPrice;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "job_id_job", nullable = false)
    private String jobId;

    // Constructores, getters y setters
    public PriceDTO() {
    }

    public PriceDTO(Long priceId, String image, String code, String name, String urlPrice, Timestamp date, String jobId) {
        this.priceId = priceId;
        this.image = image;
        this.code = code;
        this.name = name;
        this.urlPrice = urlPrice;
        this.date = date;
        this.jobId = jobId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPrice() {
        return urlPrice;
    }

    public void setUrlPrice(String urlPrice) {
        this.urlPrice = urlPrice;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
