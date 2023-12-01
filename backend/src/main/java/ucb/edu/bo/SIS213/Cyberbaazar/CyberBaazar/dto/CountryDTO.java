package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Country")
public class CountryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    private Long countryId;

    @Column(name = "code", length = 5, nullable = false)
    private String code;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    // Constructores, getters y setters
    public CountryDTO() {
    }

    public CountryDTO(Long countryId, String code, String name) {
        this.countryId = countryId;
        this.code = code;
        this.name = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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
}

