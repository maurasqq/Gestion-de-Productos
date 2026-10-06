package ni.edu.uam.gestionproductos.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ni.edu.uam.gestionproductos.entity.Etiqueta;
import ni.edu.uam.gestionproductos.dto.EtiquetaRequestDTO;
import ni.edu.uam.gestionproductos.repository.EtiquetaRepository;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {
    private final EtiquetaRepository repository;
    public EtiquetaController(EtiquetaRepository repository) { this.repository = repository; }
    @GetMapping
    public List<Etiqueta> listar() { return repository.findAll(); }
    @PostMapping
    public Etiqueta crear(@RequestBody EtiquetaRequestDTO dto) {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre(dto.getNombre());
        return repository.save(etiqueta);
    }
}
