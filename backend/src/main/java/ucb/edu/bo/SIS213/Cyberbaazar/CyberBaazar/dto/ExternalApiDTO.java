package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

public class ExternalApiDTO {
    private String source;
    private String country;
    private String values;

    public ExternalApiDTO() {
    }

    public ExternalApiDTO(String source, String country, String values) {
        this.source = source;
        this.country = country;
        this.values = values;
    }

    // Getters y setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    //toString
    @Override
    public String toString() {
        return "ExternalApiDTO{" + "source=" + source + ", country=" + country + ", values=" + values + '}';
    }
}


