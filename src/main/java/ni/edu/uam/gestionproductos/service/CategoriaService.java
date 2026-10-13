package ni.edu.uam.gestionproductos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ni.edu.uam.gestionproductos.dto.CategoriaRequestDTO;
import ni.edu.uam.gestionproductos.entity.Categoria;
import ni.edu.uam.gestionproductos.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    public Categoria actualizar(Integer id, CategoriaRequestDTO dto) {
        Categoria categoria = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
        categoria.setNombre(dto.getNombre());
        categoria.setActiva(dto.isActiva());
        return repository.save(categoria);
    }

    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada");
        }
        repository.deleteById(id);
    }
}
