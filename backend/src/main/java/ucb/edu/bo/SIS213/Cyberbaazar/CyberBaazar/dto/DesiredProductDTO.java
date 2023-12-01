package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DesiredProducts")
public class DesiredProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dproduct")
    private Long desiredProductId;

    @Column(name = "saved_date", nullable = false)
    private Date savedDate;

    @Column(name = "status", nullable = false)
    private Long status;

    @Column(name = "Users_id_user", nullable = false)
    private Long userId;

    // Constructores, getters y setters
    public DesiredProductDTO() {
    }

    public DesiredProductDTO(Long desiredProductId, Date savedDate, Long status, Long userId) {
        this.desiredProductId = desiredProductId;
        this.savedDate = savedDate;
        this.status = status;
        this.userId = userId;
    }

    public Long getDesiredProductId() {
        return desiredProductId;
    }

    public void setDesiredProductId(Long desiredProductId) {
        this.desiredProductId = desiredProductId;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
