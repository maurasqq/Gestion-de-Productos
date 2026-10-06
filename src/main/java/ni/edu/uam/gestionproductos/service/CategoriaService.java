package ni.edu.uam.gestionproductos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ni.edu.uam.gestionproductos.dto.CategoriaRequestDTO;
import ni.edu.uam.gestionproductos.entity.Categoria;
import ni.edu.uam.gestionproductos.repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria crear(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setActiva(dto.isActiva());
        return repository.save(categoria);
    }
}
