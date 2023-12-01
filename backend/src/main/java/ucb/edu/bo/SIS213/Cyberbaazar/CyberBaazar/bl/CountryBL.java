package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dao.CountryDAO;
import ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar.dto.CountryDTO;

import java.util.List;
import java.util.Optional;

@Service
public class CountryBL {
    @Autowired
    private CountryDAO countryDAO;

    public List<CountryDTO> findAllCountries() {
        try {
            return countryDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los países");
        }
    }

    public CountryDTO findCountryById(Long id) {
        try {
            Optional<CountryDTO> countryDTO = countryDAO.findById(id);
            if (countryDTO.isPresent()) {
                return countryDTO.get();
            } else {
                throw new RuntimeException("País no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CountryDTO saveCountry(CountryDTO countryDTO) {
        try {
            return countryDAO.save(countryDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el país");
        }
    }

    public CountryDTO deleteCountryById(Long id) {
        CountryDTO countryDTO = findCountryById(id);
        if (countryDTO != null) {
            countryDAO.deleteById(id);
        }
        return countryDTO;
    }

    public CountryDTO updateCountry(CountryDTO countryDTO) {
        try {
            return countryDAO.save(countryDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el país");
        }
    }
}

