package ni.edu.uam.gestionproductos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ni.edu.uam.gestionproductos.dto.ProveedorRequestDTO;
import ni.edu.uam.gestionproductos.entity.Proveedor;
import ni.edu.uam.gestionproductos.repository.ProveedorRepository;

@Service
public class ProveedorService {
    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public List<Proveedor> listar() {
        return repository.findAll();
    }

    public Proveedor crear(ProveedorRequestDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.getNombre());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreo(dto.getCorreo());
        proveedor.setActivo(dto.isActivo());
        return repository.save(proveedor);
    }
}
