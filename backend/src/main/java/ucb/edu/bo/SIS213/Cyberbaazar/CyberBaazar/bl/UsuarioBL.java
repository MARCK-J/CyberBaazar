package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.UsuarioDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.UsuarioDTO;
import org.springframework.validation.BindingResult;

@Service
public class UsuarioBL  {
    @Autowired
    private UsuarioDAO usuarioDAO;

    public UsuarioDTO save(UsuarioDTO usuarioEntity, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return usuarioDAO.save(usuarioEntity);
    }

    public UsuarioDTO findUsuarioById(Integer usuarioId) {
        return usuarioDAO.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Iterable<UsuarioDTO> findAllUsuarios() {
        try {
            return usuarioDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los usuarios");
        }
    }

    public void deleteUsuarioById(Integer usuarioId) {
        if (usuarioDAO.findById(usuarioId).isPresent()) {
            usuarioDAO.deleteById(usuarioId);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public UsuarioDTO updateUsuario(UsuarioDTO usuarioEntity, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (usuarioDAO.findById(usuarioEntity.getUsuarioId()).isPresent()) {
            return usuarioDAO.save(usuarioEntity);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
}