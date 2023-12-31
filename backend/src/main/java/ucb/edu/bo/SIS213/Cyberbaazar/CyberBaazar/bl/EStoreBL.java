package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.EStoreDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.EStoreDTO;

import java.util.List;
import java.util.Optional;

@Service
public class EStoreBL {
    private EStoreDAO eStoreDAO;

    @Autowired
    public EStoreBL(EStoreDAO eStoreDAO) {
        this.eStoreDAO = eStoreDAO;
    }
    

    public List<EStoreDTO> findAllEStores() {
        try {
            return eStoreDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las tiendas");
        }
    }

    public EStoreDTO findEStoreById(Long id) {
        try {
            Optional<EStoreDTO> eStoreDTO = eStoreDAO.findById(id);
            if (eStoreDTO.isPresent()) {
                return eStoreDTO.get();
            } else {
                throw new RuntimeException("Tienda no encontrada");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public EStoreDTO saveEStore(EStoreDTO eStoreDTO) {
        try {
            return eStoreDAO.save(eStoreDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la tienda");
        }
    }

    public EStoreDTO deleteEStoreById(Long id) {
        EStoreDTO eStoreDTO = findEStoreById(id);
        if (eStoreDTO != null) {
            eStoreDAO.deleteById(id);
        }
        return eStoreDTO;
    }

    public EStoreDTO updateEStore(EStoreDTO eStoreDTO) {
        try {
            return eStoreDAO.save(eStoreDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la tienda");
        }
    }

    public EStoreDTO findEStoreByName(String name) {
        try {
            Optional<EStoreDTO> eStoreDTO = eStoreDAO.findByName(name);
            if (eStoreDTO.isPresent()) {
                return eStoreDTO.get();
            } else {
                throw new RuntimeException("Tienda no encontrada");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
