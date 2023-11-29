package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ConsultasChatGPT")
public class ConsultasGptDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Integer idConsulta;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "pregunta", columnDefinition = "TEXT")
    private String pregunta;

    @Column(name = "respuesta", columnDefinition = "TEXT")
    private String respuesta;

    @Column(name = "fecha_consulta")
    private Timestamp fechaConsulta;

    // Constructores, getters y setters
    public ConsultasGptDTO() {
    }

    public ConsultasGptDTO(Integer idConsulta, Integer idUsuario, Integer idProducto, String pregunta, String respuesta, Timestamp fechaConsulta) {
        this.idConsulta = idConsulta;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.fechaConsulta = fechaConsulta;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Timestamp getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Timestamp fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
}

