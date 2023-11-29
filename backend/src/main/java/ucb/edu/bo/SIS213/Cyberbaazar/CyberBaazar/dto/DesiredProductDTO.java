package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DesiredProducts")
public class DesiredProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dproduct")
    private Integer desiredProductId;

    @Column(name = "saved_date", nullable = false)
    private Date savedDate;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "Users_id_user", nullable = false)
    private Integer userId;

    // Constructores, getters y setters
    public DesiredProductDTO() {
    }

    public DesiredProductDTO(Integer desiredProductId, Date savedDate, Integer status, Integer userId) {
        this.desiredProductId = desiredProductId;
        this.savedDate = savedDate;
        this.status = status;
        this.userId = userId;
    }

    public Integer getDesiredProductId() {
        return desiredProductId;
    }

    public void setDesiredProductId(Integer desiredProductId) {
        this.desiredProductId = desiredProductId;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
