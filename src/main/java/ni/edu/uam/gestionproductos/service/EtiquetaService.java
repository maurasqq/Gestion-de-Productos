package ni.edu.uam.gestionproductos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ni.edu.uam.gestionproductos.dto.EtiquetaRequestDTO;
import ni.edu.uam.gestionproductos.entity.Etiqueta;
import ni.edu.uam.gestionproductos.repository.EtiquetaRepository;

@Service
public class EtiquetaService {
    private final EtiquetaRepository repository;

    public EtiquetaService(EtiquetaRepository repository) {
        this.repository = repository;
    }

    public List<Etiqueta> listar() {
        return repository.findAll();
    }

    public Etiqueta crear(EtiquetaRequestDTO dto) {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre(dto.getNombre());
        return repository.save(etiqueta);
    }
}
