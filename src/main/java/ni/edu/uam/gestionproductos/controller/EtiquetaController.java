package ni.edu.uam.gestionproductos.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ni.edu.uam.gestionproductos.dto.EtiquetaRequestDTO;
import ni.edu.uam.gestionproductos.entity.Etiqueta;
import ni.edu.uam.gestionproductos.service.EtiquetaService;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {
    private final EtiquetaService service;
    public EtiquetaController(EtiquetaService service) { this.service = service; }
    @GetMapping
    public List<Etiqueta> listar() { return service.listar(); }
    @PostMapping
    public Etiqueta crear(@RequestBody EtiquetaRequestDTO dto) {
        return service.crear(dto);
    }
}
