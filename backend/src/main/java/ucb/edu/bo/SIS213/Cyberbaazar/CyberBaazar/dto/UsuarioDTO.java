package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
@Table(name = "Usuarios")
public class UsuarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer usuarioId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Column(name = "correo_electronico")
    private String correoElectronico;

    // Constructores, getters y setters
    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer usuarioId, String nombre, String correoElectronico) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
