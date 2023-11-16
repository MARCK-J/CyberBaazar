package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.ConsultasGptDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.ConsultasGptDTO;
import org.springframework.validation.BindingResult;

@Service
public class ConsultasGptBL {
    @Autowired
    private ConsultasGptDAO consultasGptDAO;

    public ConsultasGptDTO save(ConsultasGptDTO consultasGptDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return consultasGptDAO.save(consultasGptDTO);
    }

    public ConsultasGptDTO findConsultasById(Integer consultaId) {
        return consultasGptDAO.findById(consultaId).orElseThrow(() -> new RuntimeException("Consulta no encontrada"));
    }

    public Iterable<ConsultasGptDTO> findAllConsultas() {
        try {
            return consultasGptDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las consultas");
        }
    }

    public void deleteConsultasById(Integer consultaId) {
        if (consultasGptDAO.findById(consultaId).isPresent()) {
            consultasGptDAO.deleteById(consultaId);
        } else {
            throw new RuntimeException("Consulta no encontrada");
        }
    }

    public ConsultasGptDTO updateConsultas(ConsultasGptDTO consultasGptDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (consultasGptDAO.findById(consultasGptDTO.getIdConsulta()).isPresent()) {
            return consultasGptDAO.save(consultasGptDTO);
        } else {
            throw new RuntimeException("Consulta no encontrada");
        }
    }
}
